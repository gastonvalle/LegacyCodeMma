package mma.legacy.interval;


/**
 * Clase factoria para contruir intervals
 * @author Agustin
 *
 */
public abstract class IntervalFactory {

	
	
	/**
	 * Construye un intervalo con las propiedades especificadas
	 * @param minimum extremo inferior del intervalo 
	 * @param maximum extremo superior del intervalo
	 * @param intervalType tipo de intervalo
	 * @return nuevo intervalo
	 */
	public static Interval getInterval(double minimum, double maximum, IntervalType intervalType) {
		return new Interval(minimum, maximum, intervalType);
	}
}
