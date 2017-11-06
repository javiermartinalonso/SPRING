package es.jmartin.springshell;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jline.utils.AttributedString;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.shell.Availability;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class SpringShellApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringShellApplication.class, args);
	}
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Person{
	
	private Long id;
	private String name;
}

@Service
class PersonService implements InitializingBean
{
	private final Map <Long, Person> people = new ConcurrentHashMap<>();
	
	private final AtomicBoolean connected = new AtomicBoolean();
	
	boolean isConnected () {
		return this.connected.get();
	}
	
	void connect (String usr, String pw)
	{
		this.connected.set(true);
	}
	
	
	void disconnect() {
		this.connected.set(false);
	}
	
	Person findById(Long id) {
		return this.people.get(id);
	}
	
	Collection<Person> findByName(String name)
	{
		return this.people.values()
				.stream()
				.filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		//generacion automatica de id´s
		AtomicLong ids = new AtomicLong();
		
		Map<Long, Person> personMap = Stream.of("Brian Dussault", "Brian Clozel", "Stephane Maldini", "Stephene Nicoll",
				"James Watters", "James Bayer", "Cornelia Davis", "Madhura Brave")
		.map(name -> new Person(ids.incrementAndGet(), name))
		.collect(Collectors.toMap(Person::getId, p -> p));
		
		this.people.putAll(personMap);
		
	}
	
}

@Service
class ConsoleService
{
	private final static String ANSI_YELLOW = "\u001B[33m";
	private final static String ANSI_RESET = "\u001B[0m";
	
	private final PrintStream out = System.out;
	
	public void write (String msg, String ... args) {
		this.out.print("> ");
		this.out.print(ANSI_YELLOW);
		this.out.printf (msg, (Object[]) args);
		this.out.print(ANSI_RESET);
		this.out.println();
	}
}

@Component
class PersonConverter implements Converter<String , Person>
{
	private PersonService ps;
	
	private final Pattern ppatern  = Pattern.compile("\\(#(\\d+)\\).*");
	
	
	

	public PersonConverter(PersonService ps) {
		super();
		this.ps = ps;
	}
	
	//(#42) foo bar
	
	@Nullable
	@Override
	public Person convert(String source) {
		
		Matcher matcher = this.ppatern.matcher(source);
		
		if(matcher.find())
		{
			String group = matcher.group(1);
			if (StringUtils.hasText(group)) {
				Long id = Long.parseLong(group);
				return this.ps.findById(id);
			}
		}
		
		return null;
	}
}

@Component
class PersonValueProvider implements ValueProvider{

	private final PersonService ps;
	
	PersonValueProvider(PersonService ps){
		this.ps = ps;
	}
	
	@Override
	public List<CompletionProposal> complete(MethodParameter parameter, 
			CompletionContext completionContext, 
			String[] hints) {
		String currentInput = completionContext.currentWordUpToCursor();
		return this.ps
		.findByName(currentInput)
		.stream()
//		.map(p -> String.format(s:"(#%s) %s"), p.getId(), p.getName())
		.map(p -> String.format("(#" + p.getId() + ") " + p.getName()))
		.map(CompletionProposal::new)
		.collect(Collectors.toList());
	}

	@Override
	public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
		return parameter.getParameterType().isAssignableFrom(Person.class);
	}
	
}


@ShellComponent
class PeopleCommands{
	
	private final ConsoleService console;
	
	public PeopleCommands(ConsoleService console) {
		super();
		this.console = console;
	}


	@ShellMethod("interact with the directory")
	public void directory( @ShellOption(valueProvider = PersonValueProvider.class) Person person) {
		this.console.write("working with ", person.getName());
	}
}

@Component
class ConnectedPromptProvider implements PromptProvider{

	private final PersonService ps;
	
	public ConnectedPromptProvider(PersonService ps) {
		this.ps = ps;
	}
	
	@Override
	public AttributedString getPrompt() {
		String msg = String.format("spring PersonService (%s)", this.ps.isConnected()? "connected" : "disconnected");
		return new AttributedString(msg);
	}
	
	
}





@ShellComponent
class ConnectionCommands{
	
	private final ConsoleService console;
	private final PersonService ps;
	
	ConnectionCommands(ConsoleService console, PersonService ps){
		this.console = console;
		this.ps = ps;
	}
	
	@ShellMethod("Connect to the PersonService")
	public void connect (String username, String password)
	{
		this.ps.connect(username, password);
		this.console.write("connected %s.", username);
	}
	
	
	@ShellMethod("disconnect to the PersonService")
	public void disconnect ()
	{
		this.ps.disconnect();
	}	

	
//	@ShellMethodAvailability ("*")
	Availability connectAvalibity()
	{
		return !this.ps.isConnected() ?
				Availability.available() : Availability.unavailable("you´re already connected");				
	}
	
//	@ShellMethodAvailability ("*")
	Availability disconnectAvalibity()
	{
		return this.ps.isConnected() ?
				Availability.available() : Availability.unavailable("you´re not connected");				
	}
	
}