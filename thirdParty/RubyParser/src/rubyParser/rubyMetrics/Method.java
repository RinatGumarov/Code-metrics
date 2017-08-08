package rubyMetrics;

import java.util.ArrayList;

class Method implements Comparable<Method>{
		String name;
		ArrayList<String> args = new ArrayList<>();
		ArrayList<String> attributesUsed = new ArrayList<>();
		
		public ArrayList<String> getAttributesUsed() {
			return attributesUsed;
		}

		public void setAttributesUsed(ArrayList<String> attributesUsed) {
			this.attributesUsed = attributesUsed;
		}
		
		public void addAttributesUsed(ArrayList<String> attributesUsed) {
			this.attributesUsed.addAll(attributesUsed);
		}

		public Method(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ArrayList<String> getArgs() {
			return args;
		}

		public void setArgs(ArrayList<String> args) {
			this.args = args;
		}
		
		public String toString() {
			return this.name;
		}

		@Override
		public int compareTo(Method o) {
			//if (this.name.equals(o.name)) {
			return this.name.compareTo(o.name);
		}
		
		
		
	}