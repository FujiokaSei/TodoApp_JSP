package domain;

public class Users {
	private Integer id;
	private String name;

	//コンストラクタの定義
	public Users() {}
	public Users(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	//getter,setterの定義
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
