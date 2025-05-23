class B {
        private final Supplier<String> call_me;
    
        // Constructor injection
        B(Supplier<String> call_me) {
                this.call_me = call_me;
                final var c = this.call_me.get();
                System.out.println(c);
        }
}
;
javap(B.class)
B b = new B(() -> "definitely" + 42);