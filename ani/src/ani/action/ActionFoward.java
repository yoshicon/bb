package ani.action;

public class ActionFoward {
	private boolean isFoward = false;
	private String path = null;	// 여기가 오류 뜨는 원인 같음
	
	public boolean isFoward() {
		return isFoward;
	}
	public void setFoward(boolean isFoward) {
		this.isFoward = isFoward;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "ActionFoward [isFoward=" + isFoward + ", path=" + path + "]";
	}
	
}
