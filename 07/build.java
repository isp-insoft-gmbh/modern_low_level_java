import module java.base;

void main()
throws Exception
{
	var start = System.nanoTime();
	var source = Paths.get("source");
	var output = Paths.get("output");
	var keys = Paths.get("keys.jks");

	var key_alias = "jar";
	var key_pw = "popopo";

	if(!Files.exists(keys)) {
		printf("BOOTSTRAP: Creating private signing key%n");
		exec(
			"keytool",
			"-genkeypair",
			"-alias", key_alias,
			"-keyalg", "Ed25519",
			"-keystore", keys.toString(),
			"-validity", "365",
			"-storepass", key_pw,
			"-keypass", key_pw,
			"-dname", "cn=Oliver Jan Krylow, ou=R&D, o=isp-insoft GmbH, c=DE"
		);
	}

	printf("CLEAN: Deleting %s%n", output);
	deleteRecursively(output);

	printf("COMPILE: Compiling modules in %s%n", source);
	javac("@compile");

	printf("PACKAGE: Building jar%n");
	jar("@library");

	printf("DOCUMENTATION: Building JavaDoc-Site%n");
	javadoc("@docs");

	printf("PACKAGE: Sign jar%n");
	exec(
		"jarsigner",
		"-verbose",
		"-keystore", keys.toString(),
		"-storepass", key_pw,
		"-keypass", key_pw,
		output.resolve("lib.jar").toString(),
		key_alias
	);
	printf("PACKAGE: Build app image for cli%n");
	jpackage("@package");

	var end = System.nanoTime();
	printf("%nDONE! (%d ms)%n", (end - start)/1_000_000);
}

void deleteRecursively(Path path)
throws IOException
{
	if(!Files.exists(path)) return;
	Files.walk(path)
				.sorted(Comparator.reverseOrder())
				.forEach(p -> {
						try { Files.deleteIfExists(p); }
						catch (IOException e) { throw new UncheckedIOException(e); }
				});
}

void jar(String... args) { run("jar", args); }
void javac(String... args) { run("javac", args); }
void javadoc(String... args) { run("javadoc", args); }
void javap(String... args) { run("javap", args); }
void jdeps(String... args) { run("jdeps", args); }
void jlink(String... args) { run("jlink", args); }
void jmod(String... args) { run("jmod", args); }
void jpackage(String... args) { run("jpackage", args); }
void run(String name, String... args) {
    var tool = java.util.spi.ToolProvider.findFirst(name);
    if (tool.isEmpty()) throw new RuntimeException("No such tool found: " + name);
    var code = tool.get().run(System.out, System.err, args);
    if (code == 0) return;
    System.err.println(name + " returned non-zero exit code: " + code);
}
int exec(String... command)
throws IOException
{
	return new ProcessBuilder(command)
	.inheritIO()
	.start()
	.onExit()
	.join()
	.exitValue()
	;
}

void print(boolean b) { System.out.print(b); }
void print(char c) { System.out.print(c); }
void print(int i) { System.out.print(i); }
void print(long l) { System.out.print(l); }
void print(float f) { System.out.print(f); }
void print(double d) { System.out.print(d); }
void print(char s[]) { System.out.print(s); }
void print(String s) { System.out.print(s); }
void print(Object obj) { System.out.print(obj); }
void println() { System.out.println(); }
void println(boolean b) { System.out.println(b); }
void println(char c) { System.out.println(c); }
void println(int i) { System.out.println(i); }
void println(long l) { System.out.println(l); }
void println(float f) { System.out.println(f); }
void println(double d) { System.out.println(d); }
void println(char s[]) { System.out.println(s); }
void println(String s) { System.out.println(s); }
void println(Object obj) { System.out.println(obj); }
void printf(java.util.Locale l, String format, Object... args) { System.out.printf(l, format, args); }
void printf(String format, Object... args) { System.out.printf(format, args); }
