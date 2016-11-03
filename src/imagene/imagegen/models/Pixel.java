package imagene.imagegen.models;

/*****************************************
 * Written by Callum McLennan (s3367407) *
 * and Dorothea Baker (s3367422)         *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public class Pixel
{
	private Channel r, g, b;

	public Pixel(int r, int g, int b)
	{
		this((short)r, (short)g, (short)b);
	}

	public Pixel(double r, double g, double b)
	{
		this((short)Math.round(r), (short)Math.round(g), (short)Math.round(b));
	}

	public Pixel(short r, short g, short b)
	{
		this.r = set(r);
		this.g = set(g);
		this.b = set(b);
	}

	public Channel r() { return this.r; }
	public Channel g() { return this.g; }
	public Channel b() { return this.b; }

	public String ToString()
	{
		return String.format("[%3d,", this.r.value()) + String.format("%3d,", this.g.value()) + String.format("%3d]", this.b.value());
	}
	
	public Channel set(Channel channel)
	{
		short result;
		short value = channel.value();

		if(value % 255 == 0)
			result = (short) 0;
		else
			result = (short) (value % 255);
		
		if(result < 0) result += 255;

		channel.setValue(result);
		
		return channel;
	}

	public Channel set(short value)
	{
		Channel c = new Channel((short)Math.round(value));
		return c;
	}

	public Channel setR(short value)
	{
		Channel c = new Channel((short)Math.round(value));
		return c;
	}
	public Channel setG(short value)
	{
		Channel c = new Channel((short)Math.round(value));
		return c;
	}
	public Channel setB(short value)
	{
		Channel c = new Channel((short)Math.round(value));
		return c;
	}
}
