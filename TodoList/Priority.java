public enum Priority {
	HIGH(3),
	MEDIUM(2),
	LOW(1);

	private int propertyLevel;

	Priority(int propertyLevel) {
		this.propertyLevel = propertyLevel;
	}

	public int getPropertyLevel() {
		return propertyLevel;
	}
}
