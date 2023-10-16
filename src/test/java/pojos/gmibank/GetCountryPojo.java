package pojos.gmibank;

import java.io.Serializable;
import java.util.List;

public class GetCountryPojo implements Serializable {
	private int id;
	private String name;
	private List<StatesPojo> states;

	public GetCountryPojo() {
	}

	public GetCountryPojo(int id, String name, List<StatesPojo> states) {
		this.id = id;
		this.name = name;
		this.states = states;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StatesPojo> getStates() {
		return states;
	}

	public void setStates(List<StatesPojo> states) {
		this.states = states;
	}
}