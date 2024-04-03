package com.pandiaaman.graphql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.pandiaaman.graphql.model.Player;
import com.pandiaaman.graphql.model.Team;

import jakarta.annotation.PostConstruct;

@Service
public class PlayerService {

	private List<Player> players = new ArrayList<Player>();
	
	AtomicInteger id = new AtomicInteger(0);
	
	public List<Player> findAllPlayers(){
		return players;
	}
	
	public Optional<Player> findOnePlayer(int id){
		return players.stream().filter(x -> x.id()==id).findFirst();
	}
	
	public Player createPlayer(String name, Team team) {
		Player player = new Player(id.incrementAndGet(), name, team);
		players.add(player);
		return player;
	}
	
	public Player deletePlayer(int id) {
		Player player = players.stream().filter(x -> x.id()==id).findFirst().orElseThrow(() -> new IllegalArgumentException());
		players.remove(player);
		return player;
	}
	
	public Player updatePlayer(int id, String name, Team team) {
		Player updatedPlayer = new Player(id, name, team);
		Optional<Player> optionalPlayer = players.stream().filter(x -> x.id() == id).findFirst();
		if(optionalPlayer.isPresent()) {
			Player player = optionalPlayer.get();
			int index = players.indexOf(player);
			players.set(index, updatedPlayer);
		}else {
			throw new IllegalArgumentException("Invalid player");
		}
		return updatedPlayer;
	}
	
	
	// to add the data on starting the application, we add it in post construct phase
	@PostConstruct
	private void init() {
		players.add(new Player(id.incrementAndGet(), "Sanju Samson", Team.RR));
		players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
		players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
		players.add(new Player(id.incrementAndGet(), "Jos Buttler", Team.RR));
		players.add(new Player(id.incrementAndGet(), "Jason Boult", Team.RR));
		players.add(new Player(id.incrementAndGet(), "Hardik Pandya", Team.MI));
		players.add(new Player(id.incrementAndGet(), "Ravi Bishnoi", Team.DC));
		players.add(new Player(id.incrementAndGet(), "Virat Kohli", Team.RCB));
	}
	
}
