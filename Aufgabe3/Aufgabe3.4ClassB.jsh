class B {
     private final Supplier<String> call_me = () -> "maybe" + 7;
     B() {
         final var c = call_me.get();
         System.out.println(c);
     }
 }

/open TOOLING
javap(B.class)
