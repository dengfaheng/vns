package utility;

import utility.impl.PointFactory2DImpl;
import utility.impl.PointFactory3DImpl;
import utility.impl.PointFactory4DImpl;
import utility.impl.PointFactoryMultiDimensionImp;

public class Factory {
	
	private static PointFactory pointFactory = null;

	public static PointFactory getPointFactory() {
		return pointFactory;
	}
	
	public static void setPointFactory(PointFactory pointFactory) {
		Factory.pointFactory = pointFactory;
	}

	public static void setDimension(int dimension) {
		if(dimension == 2) pointFactory = new PointFactory2DImpl();
		if(dimension == 3) pointFactory = new PointFactory3DImpl();
		if(dimension == 4) pointFactory = new PointFactory4DImpl();
		if(dimension > 4) pointFactory = new PointFactoryMultiDimensionImp();
	}
	
	public static void setDimension(String input) {
		if(input.equalsIgnoreCase("MultiD")) pointFactory = new PointFactoryMultiDimensionImp();
	}
}
