package net.hermesengine.imported;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

public class MethodArgset {
	
	public static class Argument {
		private String full;
		public String Arg;
		public String[] Options;
		public String[] Subargs;
		public Argument(String arg, String[] opt, String[] sub) {
			this.Arg = arg;
			this.Options = opt;
			this.Subargs = sub; }
		public String get() { return this.full; }
	}
	
	public static String GetUseInfo() {
		/*
		 * "\u001b[3m" + "--HOW TO USE--" + "\u001b[0m" + "\n
		 * \n + "
		 * \u001b[31m" + "--example-arg" + "\u001b[36m" + "+these,+are,+options" + "\u001b[93m" + ":" + 
		 * "\u001b[32m" + "(some,subarguments)" + "\u001b[0m" + "\n
		 * \n + "
		 * \u001b[31m" + "--example-arg" + "\u001b[0m" + ": This is the actual argument\n" + "
		 * \u001b[36m" + "+these,+are,+options" + "\u001b[0m" + ": These are the options, they are basically settings for the argument\n" + "
		 * \u001b[93m" + ":" + "\u001b[32m" + "(some,subarguments)" + "\u001b[0m" + ": This are sub arguments, essentially flat-out\n
		 *                       arguments for the argument. Parantheses are optional, but generally are used when there is more than one\n
		 *                       subargument.\n
		 * \n + "
		 * \u001b[3m" + "--INITIALIZATION--" + "\u001b[0m" + "\n
		 * \n
		 * " + "\u001b[34m" + "MethodArgset" + "\u001b[0m" + " methodargs = " + "\u001b[31m" + "new " + "\u001b[32m" + "MethodArgset(...);\n
		 * \n
		 * ... = A string array that contains each argument, along with all of their respective options and subargs as strings (ex. above)"
		 * 
		 */
		return "\n" + "\u001b[3m" + "	--HOW TO USE--" + "\u001b[0m" + "\n\n" + "\u001b[31m" + "	--example-arg" + "\u001b[36m" + "+these,+are,+options" + "\u001b[93m" + ":" + "\u001b[32m" + "(some,subarguments)" + "\u001b[0m" + "\n\n\n" + "\u001b[31m" + "	--example-arg" + "\u001b[0m" + ": This is the actual argument\n\n" + "\u001b[36m" + "	+these,+are,+options" + "\u001b[0m" + ": These are the options, they are basically settings for the argument\n\n" + "\u001b[93m" + "	:" + "\u001b[32m" + "(some,subarguments)" + "\u001b[0m" + ": This are sub arguments, essentially flat-out\n                              arguments for the argument. Parantheses are optional, but generally are used when there is more than one\n                              subargument.\n\n" + "\u001b[3m" + "	--INITIALIZATION--" + "\u001b[0m" + "\n\n" + "\u001b[34m" + "	MethodArgset" + "\u001b[0m" + " methodargs = " + "\u001b[31m" + "new " + "\u001b[32m" + "MethodArgset(...);\n\n 	... = A string array that contains each argument, along with all of their respective options and subargs as strings (ex. above)";
	}
	
	public static void PrintUseInfo() { System.out.println(GetUseInfo() + "\n"); }

	public synchronized String[] IngestOptions(String str) {
		for ( int i = 0 ; i < str.length() ; i++ ) {
			if ( str.charAt(i) == ':' ) { str = str.substring(0, i) ; break ; }}
		str = "+" + str.substring(1).replaceAll("+", ",+");
		str = str.replaceAll(",,+", ",+");
		return str.split(",");
	}
	
	public synchronized String[] IngestSubargs(String str) {
		if ( str.substring(0, 1) == ":(" ) { str = str.substring(2); }
		if ( str.charAt(0) == ':' || str.charAt(0) == '(' ) { str = str.substring(1); }
		if ( str.charAt( str.length() ) == ')' ) { str = str.substring(0, (str.length() - 1)); }
		str = str.replaceAll(", ", ",");
		return str.split(",");
	}
	
	public String[] args;
	public Argument[] argv;
	public int argc;
	
	public MethodArgset(String[] args) {
		for ( int argindex = 0 ; argindex < args.length ; argindex++ ) {
			String str = args[argindex];
			this.args[argindex] = str;
			
			int opt_start = -1;
			int sub_start = -1;
			for ( int i = 0 ; i < str.length() ; i++ ) {
				if ( str.charAt(i) == '+' ) { opt_start = i ; break ; }
			} for ( int i = opt_start ; i < str.length() ; i++ ) {
				if ( str.charAt(i) == ':' ) { sub_start = i ; break ; }
			}
			String arg = str.substring(0, opt_start);
			String options = str.substring(opt_start, sub_start).replaceAll(", ", ",");
			options = options.replaceAll(" +", ",+");
			options = options.replaceAll("+", ",+");
			options = options.replaceAll(",,,+", ",+");
			options = options.replaceAll(",,+", ",+");
			String sub = str.substring(sub_start);
			if ( sub.substring(0, 1) == ":(" ) { sub = sub.substring(2); }
			else if ( sub.charAt(0) == ':' || sub.charAt(0) == '(' ) { sub = sub.substring(1); }
			if ( sub.charAt(sub.length()) == ')' ) { sub = sub.substring(0, sub.length() - 1); }
			this.argv[argindex] = new Argument( arg, IngestOptions(options), IngestSubargs(sub) );
			this.argc++;
		}
	}
	
	public Argument get(int index) { return this.argv[index]; }
}
