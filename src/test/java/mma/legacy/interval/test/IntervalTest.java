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
	public void calcula_punto_medio_intervalo() {
		assertEquals(5, IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).midPoint(), 0.0);

	}

	@Test
	public void no_es_punto_medio_por_valor_inferior() {
		assertNotEquals(4, IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).midPoint(), 0.0);

	}

	@Test
	public void no_es_punto_medio_por_valor_superior() {
		assertNotEquals(6, IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).midPoint(), 0.0);

	}

	@Test
	public void esta_dentro_del_intervalo_cerrado_del_extremo_inferior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).includes(0));
	}

	@Test
	public void esta_dentro_del_intervalo_cerrado_del_extremo_superior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).includes(10));
	}

	@Test
	public void esta_fuera_del_intervalo_cerrado_del_extremo_inferior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).includes(-1));
	}

	@Test
	public void esta_fuera_del_intervalo_cerrado_del_extremo_superior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.CLOSED).includes(11));
	}

	@Test
	public void esta_dentro_del_intervalo_abierto_del_extremo_inferior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.OPENED).includes(1));
	}

	@Test
	public void esta_dentro_del_intervalo_abierto_del_extremo_superior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.OPENED).includes(9));
	}

	@Test
	public void esta_fuera_del_intervalo_abierto_del_extremo_inferior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.OPENED).includes(0));
	}

	@Test
	public void esta_fuera_del_intervalo_abierto_del_extremo_superior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.OPENED).includes(10));
	}

	@Test
	public void esta_dentro_del_intervalo_abierto_inferiormente_del_extremo_inferior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.LEFT_OPENED).includes(1));
	}

	@Test
	public void esta_dentro_del_intervalo_abierto_inferiormente_del_extremo_superior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.LEFT_OPENED).includes(10));
	}

	@Test
	public void esta_fuera_del_intervalo_abierto_inferiormente_del_extremo_inferior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.LEFT_OPENED).includes(0));
	}

	@Test
	public void esta_fuera_del_intervalo_abierto_inferiormente_del_extremo_superior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.LEFT_OPENED).includes(11));
	}

	@Test
	public void esta_dentro_del_intervalo_abierto_superiormente_del_extremo_inferior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.RIGHT_OPENED).includes(0));
	}

	@Test
	public void esta_dentro_del_intervalo_abierto_superiormente_del_extremo_superior() {
		assertTrue(IntervalFactory.getInterval(0, 10, IntervalType.RIGHT_OPENED).includes(9));
	}

	@Test
	public void esta_fuera_del_intervalo_abierto_superiormente_del_extremo_inferior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.RIGHT_OPENED).includes(-1));
	}

	@Test
	public void esta_fuera_del_intervalo_abierto_superiormente_del_extremo_superior() {
		assertFalse(IntervalFactory.getInterval(0, 10, IntervalType.RIGHT_OPENED).includes(10));
	}

	@Test
	public void esta_intervalo_dentro_de_otro_intervalo_cerrado() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
	}

	@Test
	public void esta_intervalo_fuera_de_otro_intervalo_cerrado_ambos_por_debajo_limite_inferior() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(10, 19, IntervalType.CLOSED)));
	}

	@Test
	public void esta_intervalo_fuera_de_otro_intervalo_cerrado_teniendo_solo_limite_superior_en_intervalo() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
	}

	@Test
	public void esta_intervalo_fuera_de_otro_intervalo_cerrado_teniendo_solo_limite_inferior_en_intervalo() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
	}

	@Test
	public void esta_intervalo_fuera_de_otro_intervalo_cerrado_ambos_por_encima_limite_superior() {
		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, IntervalType.CLOSED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(36, 40, IntervalType.CLOSED)));
	}
	
	

	@Test
	public void incluye_intervalo_cerrado_en_intervalo_cerrado() {
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.CLOSED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.CLOSED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.CLOSED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.CLOSED)));
	}
	
	@Test
	public void incluye_intervalo_abierto_superiormente_en_intervalo_cerrado() {
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.RIGHT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void incluye_intervalo_abierto_inferiormente_en_intervalo_cerrado() {
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.LEFT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.LEFT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.LEFT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.LEFT_OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void incluye_intervalo_abierto_en_intervalo_cerrado() {
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(closedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.OPENED)));
		assertFalse(closedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.OPENED)));
	}

	@Test
	public void incluye_intervalo_cerrado_en_intervalo_abierto_superiormente() {
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.CLOSED)));
	}

	@Test
	public void incluye_intervalo_abierto_superiormente_en_intervalo_abierto_superiormente() {
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void incluye_intervalo_abierto_inferiormente_en_intervalo_abierto_superiormente() {
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void incluye_intervalo_abierto_en_intervalo_abierto_superiormente() {
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.OPENED)));
	}
	

	@Test
	public void incluye_intervalo_cerrado_en_intervalo_abierto_inferiormente() {
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.CLOSED)));
	}

	@Test
	public void incluye_intervalo_abierto_superiormente_en_intervalo_abierto_inferiormente() {
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void incluye_intervalo_abierto_inferiormente_en_intervalo_abierto_inferiormente() {
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.LEFT_OPENED)));
	}

	@Test
	public void incluye_intervalo_abierto_en_intervalo_abierto_inferiormente() {
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.OPENED)));
	}
	
	
	
	@Test
	public void incluye_intervalo_cerrado_en_intervalo_abierto() {
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.CLOSED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.CLOSED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.CLOSED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.CLOSED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.CLOSED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.CLOSED)));
	}
	@Test
	public void incluye_intervalo_abierto_superiormente_en_intervalo_abierto() {
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.RIGHT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.RIGHT_OPENED)));
	}

	@Test
	public void incluye_intervalo_abierto_inferiormente_en_intervalo_abierto() {
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.LEFT_OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.LEFT_OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.LEFT_OPENED)));
	}
	
	@Test
	public void incluye_intervalo_abierto_en_intervalo_abierto() {
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(10, 15, IntervalType.OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(15, 20, IntervalType.OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(20, 25, IntervalType.OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(25, 30, IntervalType.OPENED)));
		assertTrue(openedPivot.includes(IntervalFactory.getInterval(30, 35, IntervalType.OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(35, 40, IntervalType.OPENED)));
		assertFalse(openedPivot.includes(IntervalFactory.getInterval(40, 45, IntervalType.OPENED)));
	}

	@Test
	public void hasIntersectionTest() {

		

		


		intersecciona_intervalo_cerrado_en_intervalo_abierto_inferiormente();
		Interval rightOpenedPivot = IntervalFactory.getInterval(20, 40, IntervalType.RIGHT_OPENED);
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));
		Interval unopenedPivot = IntervalFactory.getInterval(20, 40, IntervalType.CLOSED);
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.CLOSED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));
	}

	public void intersecciona_intervalo_cerrado_en_intervalo_abierto_inferiormente() {
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

	public void intersecciona_intervalo_abierto_superiormente_en_intervalo_abierto_inferiormente() {
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

	public void intersecciona_intervalo_abierto_inferiormente_en_intervalo_abierto_inferiormente() {
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

	public void intersecciona_intervalo_abierto_en_intervalo_abierto_inferiormente() {
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
	public void intersecciona_intervalo_cerrado_en_intervalo_abierto() {
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
	public void intersecciona_intervalo_abierto_superiormente_en_intervalo_abierto() {
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
	public void intersecciona_intervalo_abierto_inferiormente_en_intervalo_abierto() {
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
	public void intersecciona_intervalo_abierto_en_intervalo_abierto() {
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

	@Test
	public void hasIntersectionTest2() {

		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 40, IntervalType.OPENED);

		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.CLOSED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.CLOSED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.CLOSED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));

		Interval leftOpenedPivot = IntervalFactory.getInterval(20, 40, IntervalType.LEFT_OPENED);

		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.CLOSED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.CLOSED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));
		Interval rightOpenedPivot = IntervalFactory.getInterval(20, 40, IntervalType.RIGHT_OPENED);
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.CLOSED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));
		Interval unopenedPivot = IntervalFactory.getInterval(20, 40, IntervalType.CLOSED);
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, IntervalType.CLOSED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, IntervalType.CLOSED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, IntervalType.CLOSED)));
	}

}
