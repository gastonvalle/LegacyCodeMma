package mma.legacy.interval;


/**
 * Clase factoria para contruir intervals
 * @author Agustin
 *
 */
public class IntervalFactory {

	private IntervalFactory()
	{
		
	}
	public static Interval getInterval(double minimum, double maximum, IntervalType opening) {
		return new Interval(minimum, maximum, opening);
	}
}
