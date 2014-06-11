package nz.co.ecngroup.droolstest;

public class RuleResult {
	private String status = "invalid";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RuleResult [status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}
