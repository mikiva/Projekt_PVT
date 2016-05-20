package analysis;

public final class AnalysisTitle {

	private final String text;

	public AnalysisTitle(String text) {
		if(text == null || text.isEmpty()) throw new TitleException();
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	class TitleException extends NullPointerException {
		private static final long serialVersionUID = 1L;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalysisTitle other = (AnalysisTitle) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	
	
}
