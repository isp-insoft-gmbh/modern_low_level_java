class A {
	A() {
		final var c = call_me();
		System.out.println(c);
	}
	private String call_me() { return "maybe" + 7;}
}

/open TOOLING
javap(A.class)
