public class Name {
    private String first;
    private String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String fullname() {
        return last + ", " + first;
    }

    public boolean match(Name other) {
        return first.equalsIgnoreCase(other.first)
                && last.equalsIgnoreCase(other.last);
    }

    public boolean isLessThan(Name other) {
        int lastComparison = last.toLowerCase().compareTo(other.last.toLowerCase());

        if (lastComparison < 0) {
            return true;
        }

        if (lastComparison > 0) {
            return false;
        }

        return first.toLowerCase().compareTo(other.first.toLowerCase()) < 0;
    }

    
    public String nameToString() {
        return fullname();
    }
}