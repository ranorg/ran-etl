package com.ranorg.etl.record;

import static com.ranorg.etl.misc.ETLConstants.OUTFILE_FIELD_DELIMETER;
import static com.ranorg.etl.misc.ETLConstants.OUTFILE_FIELD_INNER_DELIMETER;

import java.util.LinkedHashSet;

public class DataRecord extends Record{

	private LinkedHashSet<Field> fields = new LinkedHashSet<Field>();

	public DataRecord() {
		fields.add(new Field("filename",""));
		fields.add(new Field("recordName",""));
	}
	
	public void addField(Field field) {
		fields.add(field);
	}

	public void setFieldValue(String name, String value) {
		for (Field fld : fields) {
			if (fld.name != null && fld.name.equals(name)) {
				fld.value = value;
			}
		}
	}

	public LinkedHashSet<Field> getFields() {
		return fields;
	}

	public Field getFields(String name) {
		for (Field fld : fields) {
			if (fld.name.equals(name))
				return fld;
		}
		return null;
	}

	public static class Field {
		private String name;
		private String value;

		public Field(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public void appendValue(String value) {
			if (this.value != null && !this.value.trim().equals(""))
				this.value += OUTFILE_FIELD_INNER_DELIMETER + value;
			else
				this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			Field other = (Field) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
		

	}

	@Override
	public String toString() {
		String ret = "";
		String del = "";
		for (Field fld : fields) {
			ret += del + fld.value;
			del = OUTFILE_FIELD_DELIMETER;
		}
		return ret;
	}
}
