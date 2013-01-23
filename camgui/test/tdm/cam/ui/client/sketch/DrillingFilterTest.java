package tdm.cam.ui.client.sketch;

import junit.framework.Assert;

import org.junit.Test;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.TestDrillingParameters;

public class DrillingFilterTest {

	@Test
	public void frontSideFilterTest1() {
		FrontDrillingFilter filter = new FrontDrillingFilter();
		ImosDrilling drilling = new TestDrillingParameters().angleX(0).angleY(0).angleZ(0).create();
		
		Assert.assertFalse(filter.isDisplayed(drilling));
	}

	@Test
	public void frontSideFilterTest2() {
		FrontDrillingFilter filter = new FrontDrillingFilter();
		ImosDrilling drilling = new TestDrillingParameters().angleX(90).angleY(0).angleZ(0).create();
		
		Assert.assertTrue(filter.isDisplayed(drilling));
	}

	@Test
	public void backSideFilterTest1() {
		BackDrillingFilter filter = new BackDrillingFilter();
		ImosDrilling drilling = new TestDrillingParameters().angleX(-180).angleY(0).angleZ(0).create();
		
		Assert.assertFalse(filter.isDisplayed(drilling));
	}

	@Test
	public void backSideFilterTest2() {
		BackDrillingFilter filter = new BackDrillingFilter();
		ImosDrilling drilling = new TestDrillingParameters().angleX(0).angleY(0).angleZ(0).create();
		
		Assert.assertTrue(filter.isDisplayed(drilling));
	}

}
