package mma.legacy.interval.test;

import static org.junit.Assert.*;
import mma.legacy.interval.Interval;
import mma.legacy.interval.IntervalFactory;
import mma.legacy.interval.IntervalType;

import org.junit.Test;

public class IntervalTest {
	
	private Interval closedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
	private Interval openedPivot = IntervalFactory.getInterval(20, 35, IntervalType.OPENED);
	private Interval leftOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.LEFT_OPENED);
	private Interval rightOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.RIGHT_OPENED);
	
	@Test
	public void calculaPuntoMmedioIntervalo() {
		assertEquals(5, IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).midPoint(), 0.0);

	}

	@Test
	public void noEsPuntoMedioPorValorInferior() {
		assertNotEquals(4, IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).midPoint(), 0.0);

	}

	@Test
	public void noEsPuntoMedioPorValorSuperior() {
		assertNotEquals(6, IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).midPoint(), 0.0);

	}

	@Test
	public void estaDentroDelIntervaloCerradoDelExtremoInferior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).includes(0));
	}

	@Test
	public void estaDentroDelIntervaloCerradoDelExtremoSuperior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).includes(10));
	}

	@Test
	public void estaFueraDelIntervaloCerradoDelExtremoInferior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).includes(-1));
	}

	@Test
	public void estaFueraDelIntervaloCerradoDelExtremoSuperior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).includes(11));
	}

	@Test
	public void estaDentroDelIntervaloAbiertoDelExtremoInferior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.OPENED).includes(1));
	}

	@Test
	public void estaDentroDelIntervaloAbiertoDelExtremoSuperior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.OPENED).includes(9));
	}

	@Test
	public void estaFueraDelIntervaloAbiertoDelExtremoInferior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.OPENED).includes(0));
	}

	@Test
	public void estafueraDelIntervaloAbiertoDelExtremoSuperior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.OPENED).includes(10));
	}

	@Test
	public void estaDentroDelIntervaloAbiertoInferiormenteDelExtremoInferior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.LEFT_OPENED).includes(1));
	}

	@Test
	public void estaDentroDelIntervaloAbiertoInferiormenteDelExtremoSuperior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.LEFT_OPENED).includes(10));
	}

	@Test
	public void estaFueraDelIntervaloAbiertoInferiormenteDelExtremoInferior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.LEFT_OPENED).includes(0));
	}

	@Test
	public void estaFueraDelIntervaloAbiertoInferiormenteDelExtremoSuperior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.LEFT_OPENED).includes(11));
	}

	@Test
	public void estaDentroDelIntervaloAbiertoSuperiormenteDelExtremoInferior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.RIGHT_OPENED).includes(0));
	}

	@Test
	public void estaDentroDelIntervaloAbiertoSuperiormenteDelExtremoSuperior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.RIGHT_OPENED).includes(9));
	}

	@Test
	public void estaFueraDelIntervaloAbiertoSuperiormenteDelExtremoInferior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.RIGHT_OPENED).includes(-1));
	}

	@Test
	public void estaFueraDelIntervaloAbiertoSuperiormenteDelExtremoSuperior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.RIGHT_OPENED).includes(10));
	}

	@Test
	public void estaIntervaloDentroDeOtroIntervaloCerrado() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
	}

	@Test
	public void estaIntervaloFueraDeOtroIntervaloCerradoAmbosPorDebajoLimiteInferior() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(10, 19, IntervalType.CLOSED)));
	}

	@Test
	public void estaIntervaloFueraDeOtroIntervaloCerradoTeniendoSoloLimiteSuperiorEnIntervalo() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
	}

	@Test
	public void estaIntervaloFueraDeOtroIntervaloCerradoTeniendoSoloLimiteInferiorEnIntervalo() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
	}

	@Test
	public void estaIntervaloFueraDeOtroIntervaloCerradoAmbosPorEncimaLimiteSuperior() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(36, 40, IntervalType.CLOSED)));
	}
	
	

	@Test
	public void incluyeIntervaloCerradoEnIntervaloCerrado() {
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.CLOSED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.CLOSED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.CLOSED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.CLOSED)));
	}
	
	@Test
	public void incluyeIntervaloAbiertoSuperiormenteEnIntervaloCerrado() {
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.RIGHT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void incluyeIntervaloAbiertoInferiormenteEnIntervaloCerrado() {
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.LEFT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.LEFT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.LEFT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.LEFT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void incluyeIntervaloAbiertoEnIntervaloCerrado() {
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.OPENED)));
	}

	@Test
	public void incluyeIntervaloCerradoEnIntervaloAbiertoSuperiormente() {
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.CLOSED)));
	}

	@Test
	public void incluyeIntervaloAbiertoSuperiormenteEnIntervaloAbiertoSuperiormente() {
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void incluyeIntervaloAbiertoInferiormenteEnIntervaloAbiertoSuperiormente() {
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void incluyeIntervaloAbiertoEnIntervaloAbiertoSuperiormente() {
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.OPENED)));
	}
	

	@Test
	public void incluyeIntervaloCerradoEnIntervaloAbiertoInferiormente() {
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.CLOSED)));
	}

	@Test
	public void incluyeIntervaloAbiertoSuperiormenteEnIntervaloAbiertoInferiormente() {
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void incluyeIntervaloAbiertoInferiormenteEnIntervaloAbiertoInferiormente() {
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void incluyeIntervaloAbiertoEnIntervaloAbiertoInferiormente() {
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.OPENED)));
	}
	
	
	
	@Test
	public void incluyeIntervaloCerradoEnIntervaloAbierto() {
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.CLOSED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.CLOSED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.CLOSED)));
	}
	@Test
	public void incluyeIntervaloAbiertoSuperiormenteEnIntervaloAbierto() {
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void incluyeIntervaloAbiertoInferiormenteEnIntervaloAbierto() {
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.LEFT_OPENED)));
	}
	
	@Test
	public void incluyeIntervaloAbiertoEnIntervaloAbierto() {
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.OPENED)));
	}

	Interval rightOpenedPivotForIntersection = IntervalFactory.getInterval(20, 40, IntervalType.RIGHT_OPENED);
	Interval unopenedPivotForIntersection = IntervalFactory.getInterval(20, 40, IntervalType.CLOSED);
	@Test
	public void interseccionaIntervaloCerradoConIntervaloCerrado() {


		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.CLOSED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.CLOSED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.CLOSED)));
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoSuperiormenteConIntervaloCerrado() {
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoInferiormenteConIntervaloCerrado() {
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.LEFT_OPENED)));
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.LEFT_OPENED)));
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoConIntervaloCerrado() {
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertTrue(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.OPENED)));
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.OPENED)));
		assertFalse(unopenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.OPENED)));
	}

	@Test
	public void interseccionaIntervaloCerradoConIntervaloAbiertoSuperiormente() {
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoSuperiormenteConIntervaloAbiertoSuperiormente() {
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
	}
	
	@Test
	public void interseccionaIntervaloAbiertonferIormenteConIntervaloAbiertoSuperiormente() {
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoConIntervaloAbiertoSuperiormente() {
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertTrue(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.OPENED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.OPENED)));
		assertFalse(rightOpenedPivotForIntersection.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.OPENED)));
	}

	@Test
	public void interseccionaIntervaloCerradoEnIntervaloAbiertoInferiormente() {
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 50, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 55, IntervalType.CLOSED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoSuperiormenteEnIntervaloAbiertoInferiormente() {
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 55, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoInferiormenteEnIntervaloAbiertoInferiormente() {
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 50, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 55, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void interseccionaIntervalAbiertoEnIntervaloAbiertoInferiormente() {
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 50, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 55, IntervalType.OPENED)));
	}

	@Test
	public void interseccionaIntervaloCerradoEnIntervaloAbierto() {
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(35, 50, IntervalType.CLOSED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));
	}
	
	
	@Test
	public void interseccionaIntervaloAbiertoSuperiormenteEnIntervaloAbierto() {
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(30, 45, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoInferiormenteEnIntervaloAbierto() {
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(35, 50, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(40, 55, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void interseccionaIntervaloAbiertoEnIntervaloAbierto() {
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertTrue(openedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(35, 50, IntervalType.OPENED)));
		assertFalse(openedPivot.intersectsWith(IntervalFactory.getInterval(40, 55, IntervalType.OPENED)));
	}

}
