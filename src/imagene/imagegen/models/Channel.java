package imagene.imagegen.models;

/****************************************
 * Written by Dorothea Baker (s3367422) *
 * for                                  *
 * Programming Project 1                *
 * SP3 2016                             *
 ***************************************/

public class Channel {
    private Short value;

    public Channel(short value) {
        this.value = value;
    }

    public short value() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
