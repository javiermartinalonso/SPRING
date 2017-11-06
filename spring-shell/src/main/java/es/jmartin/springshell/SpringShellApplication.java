package es.jmartin.springshell;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;

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



@ShellComponent
class ConnectionCommands{
	
	private final PersonService ps;
	
	ConnectionCommands(PersonService ps){
		this.ps = ps;
	}
	
	@ShellMethod("Connect to the PersonService")
	public void connect (String username, String password)
	{
		this.ps.connect(username, password);
	}
}