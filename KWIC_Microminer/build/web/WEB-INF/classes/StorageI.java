package default_package;



public interface StorageI {



        //sets lines into map, key line number, entry line
        public void setLine(int lineNumber, String line);

        //returns a  line based on given line number
        public String getLine(int lineNumber);

        //returns number of lines in the map
        public int getLineCount();


//      public String getDescriptor(int lineNumber);
//      public void setDescriptor(int lineNumber, String descriptor);
//      public void setURL(int lineNumber, String line);
//      public String getURL(int i);


}

