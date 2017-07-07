package mma.legacy.interval;

/**
 * Clase para el ejemplo de trabajo con Legacy
 * 
 * @author Agustin Controla operaciones sobre intervalos que pudeen ser abiertos
 *         o cerrados
 */
public class Interval {

	private double minimum; // número entero que indica el limite inferior del
							// intervalo
	private double maximum; // número entero que indica el limite superior del
							// intervalo
	private IntervalType intervalType; // Enumerado que indica el tipo de intervalo


	/**
	 * Constructor de la clase
	 * 
	 * @param minimum 
	 * @param maximum
	 * @param opening
	 *            Todos los parámetros pueden ser nulos
	 */
	public Interval(double minimum, double maximum, IntervalType opening) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.intervalType = opening;
	}

	/**
	 * Calcula el punto medio del intervalo
	 * 
	 * @return
	 */
	public double midPoint() {
		return (maximum + minimum) / 2;
	}

	/**
	 * Verifica si un punto dado está dentro del intervalo
	 * 
	 **/
	public boolean includes(double value) {
		
		return isLessThanMaximum(value) && isGratherThanMinimum(value);
		
	}
	
	private boolean isGratherThanMinimum(double value) {
		if(isLeftLimitOpened())
			return Double.compare(minimum, value) < 0;
		return Double.compare(minimum, value) <= 0;
	}
	
	private boolean isLessThanMaximum(double value) {
		if (isRightLimitOpened())
			return Double.compare(value, maximum) < 0;
		return Double.compare(value, maximum) <= 0;
	}
	
	private boolean isRightLimitOpened() {
		return intervalType == intervalType.OPENED || intervalType == intervalType.RIGHT_OPENED; 
	}
	
	private boolean isLeftLimitOpened() {
		return intervalType == intervalType.OPENED || intervalType == intervalType.LEFT_OPENED; 
	}

	/**
	 * Este método calcula si el intervalo @interval esta incluido dentro del propio intervalo
	 * 
	 * @param interval: intervalo a verificar
	 * @return 
	 */
	public boolean includes(Interval interval) {
		boolean minimumIncluded = this.includes(interval.minimum);
		boolean maximumIncluded = this.includes(interval.maximum);
		switch (intervalType) {
		case OPENED:
			return includesInOpenInterval(interval, minimumIncluded, maximumIncluded);
		case LEFT_OPENED:
			return includesInLeftOpenInterval(interval, minimumIncluded, maximumIncluded);
		case RIGHT_OPENED:
			return includesInRightOnepedInterval(interval, minimumIncluded, maximumIncluded);
		case CLOSED:
			return includesInClosedInterval(interval, minimumIncluded, maximumIncluded);
		default:
			return false;
		}
	}

	private boolean includesInClosedInterval(Interval interval, boolean minimumIncluded, boolean maximumIncluded) {
		switch (interval.intervalType) {
		case OPENED:
		case LEFT_OPENED:
		case RIGHT_OPENED:
		case CLOSED:
			return (minimumIncluded || Double.compare(minimum, interval.minimum) == 0)
					&& (maximumIncluded || Double.compare(maximum, interval.maximum) == 0);
		default:
			return false;
		}
	}

	private boolean includesInRightOnepedInterval(Interval interval, boolean minimumIncluded, boolean maximumIncluded) {
		switch (interval.intervalType) {
		case OPENED:
		case RIGHT_OPENED:
			return (minimumIncluded || Double.compare(minimum,interval.minimum) == 0)
					&& (maximumIncluded || Double.compare(maximum, interval.maximum) == 0);
		case LEFT_OPENED:
		case CLOSED:
			return (minimumIncluded || Double.compare(minimum, interval.minimum) == 0 ) && (maximumIncluded);
		default:
			return false;
		}
	}

	private boolean includesInLeftOpenInterval(Interval interval, boolean minimumIncluded, boolean maximumIncluded) {
		switch (interval.intervalType) {
		case OPENED:
		case LEFT_OPENED:
			return (minimumIncluded || Double.compare(minimum,interval.minimum) == 0)
					&& (maximumIncluded || Double.compare(maximum, interval.maximum) == 0);
		case RIGHT_OPENED:
		case CLOSED:
			return (minimumIncluded) && (maximumIncluded || Double.compare(maximum, interval.maximum) == 0);
		default:
			return false;
		}
	}

	private boolean includesInOpenInterval(Interval interval, boolean minimumIncluded, boolean maximumIncluded) {
		switch (interval.intervalType) {
		case OPENED:
			return (minimumIncluded || minimum == interval.minimum)
					&& (maximumIncluded || maximum == interval.maximum);
		case LEFT_OPENED:
			return (minimumIncluded || minimum == interval.minimum) && (maximumIncluded);
		case RIGHT_OPENED:
			return (minimumIncluded) && (maximumIncluded || maximum == interval.maximum);
		case CLOSED:
			return (minimumIncluded) && (maximumIncluded);
		default:
			return false;
		}
	}

	/**
	 * Este método verifica si el intervalo que recibe como parametro, intersecciona con el propio intervalo
	 * 
	 * @param interval
	 * @return
	 */

	public boolean intersectsWith(Interval interval) {
		if (minimum == interval.maximum) {
			switch (intervalType) {
			case OPENED:
			case LEFT_OPENED:
				return false;
			case RIGHT_OPENED:
			case CLOSED:
				return interval.intervalType == IntervalType.LEFT_OPENED || interval.intervalType == IntervalType.CLOSED;
			default:
				assert false;
				return false;
			}
		}
		if (maximum == interval.minimum) {
			switch (intervalType) {
			case OPENED:
			case RIGHT_OPENED:
				return false;
			case LEFT_OPENED:
			case CLOSED:
				return interval.intervalType == IntervalType.RIGHT_OPENED || interval.intervalType == IntervalType.CLOSED;
			default:
				assert false;
				return false;
			}
		}
		return this.includes(interval.minimum) || this.includes(interval.maximum);
	}

}
