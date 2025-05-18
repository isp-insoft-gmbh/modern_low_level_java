interface I {}
class C {}
enum E {}
record R() {}
/open TOOLING
/open PRINTING
println("Interface Class:")
println("================")
javap(I.class)
println("Class Class:")
println("================")
javap(C.class)
println("Enum Class:")
println("================")
javap(E.class)
println("Record Class:")
println("================")
javap(R.class)


