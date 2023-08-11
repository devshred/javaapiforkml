package de.micromata.jak;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import de.micromata.jak.internal.IAtsConformanceLevel2;
import de.micromata.opengis.kml.v_2_2_0.Alias;
import de.micromata.opengis.kml.v_2_2_0.BalloonStyle;
import de.micromata.opengis.kml.v_2_2_0.Camera;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Data;
import de.micromata.opengis.kml.v_2_2_0.ExtendedData;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.GroundOverlay;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.ImagePyramid;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LabelStyle;
import de.micromata.opengis.kml.v_2_2_0.LatLonBox;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.Link;
import de.micromata.opengis.kml.v_2_2_0.ListStyle;
import de.micromata.opengis.kml.v_2_2_0.Location;
import de.micromata.opengis.kml.v_2_2_0.Model;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.Overlay;
import de.micromata.opengis.kml.v_2_2_0.Pair;
import de.micromata.opengis.kml.v_2_2_0.PhotoOverlay;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.PolyStyle;
import de.micromata.opengis.kml.v_2_2_0.ResourceMap;
import de.micromata.opengis.kml.v_2_2_0.Scale;
import de.micromata.opengis.kml.v_2_2_0.ScreenOverlay;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.StyleMap;
import de.micromata.opengis.kml.v_2_2_0.StyleState;
import de.micromata.opengis.kml.v_2_2_0.ViewRefreshMode;

/**
 * @author Flori (f.bachmann@micromata.de)
 * 
 */
public class AtsConformanceLevel2Test implements IAtsConformanceLevel2 {
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(KmlReferencePojoTest.class.getName());

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc42PolyStyle()
	 */
	@Test
	public void atc42PolyStyle() {
		// a kml:Scale element is not a descendant of kml:Update
		Assertions.assertNull(Utils.findClass(PolyStyle.class, "update"));
		
		Assertions.assertNotNull(Utils.findField(PolyStyle.class, "color"));
		Assertions.assertNotNull(Utils.findField(PolyStyle.class, "colorMode"));
		Assertions.assertNotNull(Utils.findField(PolyStyle.class, "fill"));
		Assertions.assertNotNull(Utils.findField(PolyStyle.class, "outline"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc43CoordinatesAltitudeMode()
	 */
	@Test
	public void atc43CoordinatesAltitudeMode() {
		// Applies to the control points 
		// in kml:AbstractGeometryType/kml:coordinates 
		// and kml:Model/kml:Location.
		Field location = Utils.findField(Model.class, "location");
		Assertions.assertNotNull(location);
		Assertions.assertEquals(Location.class, location.getType());
		
		// check if LinearRing contains coordinats
		Field coordinatesLinerarRing = Utils.findField(LinearRing.class, "coordinates");
		Assertions.assertNotNull(coordinatesLinerarRing);
		Assertions.assertEquals(Coordinate.class, Utils.getClassForGenericList(coordinatesLinerarRing.getGenericType().toString()));
		
		// check if Point contains coordinats
		Field coordinatesPoint = Utils.findField(Point.class, "coordinates");
		Assertions.assertNotNull(coordinatesPoint);
		Assertions.assertEquals(Coordinate.class, Utils.getClassForGenericList(coordinatesPoint.getGenericType().toString()));
		
		// check if Model contains coordinats
		Field locationModel = Utils.findField(Model.class, "location");
		Assertions.assertNotNull(locationModel);
		Assertions.assertEquals(Location.class, locationModel.getType());
		
		// check if LineString contains coordinats
		Field coordinatesLineString = Utils.findField(LineString.class, "coordinates");
		Assertions.assertNotNull(coordinatesLineString);
		Assertions.assertEquals(Coordinate.class, Utils.getClassForGenericList(coordinatesLineString.getGenericType().toString()));
		
		// check if Location contains altitude as double type
		Field locationAltitude = Utils.findField(Location.class, "altitude");
		Assertions.assertNotNull(locationAltitude);
		Assertions.assertEquals("double", locationAltitude.getType().getSimpleName());

		// check if Location contains altitude as double type
		Field coordinateAltitude = Utils.findField(Coordinate.class, "altitude");
		Assertions.assertNotNull(coordinateAltitude);
		Assertions.assertEquals("double", coordinateAltitude.getType().getSimpleName());
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc44ScaleMinimalContent()
	 */
	@Test
	public void atc44ScaleMinimalContent() {
		// a kml:Scale element is not a descendant of kml:Update
		Assertions.assertNull(Utils.findClass(Scale.class, "Update"));
		
		Assertions.assertNotNull(Utils.findField(Scale.class, "x"));
		Assertions.assertNotNull(Utils.findField(Scale.class, "y"));
		Assertions.assertNotNull(Utils.findField(Scale.class, "z"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc45KMLMinimalContent()
	 */
	@Test
	public void atc45KMLMinimalContent() {
		Assertions.assertNotNull(Utils.findField(Kml.class, "networkLinkControl"));
		Assertions.assertNotNull(Utils.findField(Kml.class, "feature"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc46ViewFormat()
	 */
	@Test
	public void atc46ViewFormat() {
		Assertions.assertNotNull(Utils.findField(Link.class, "viewFormat"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc47HttpQuery()
	 */
	@Test
	public void atc47HttpQuery() {
		Assertions.assertNotNull(Utils.findField(Link.class, "httpQuery"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc48LinearRingInPolygon()
	 */
	@Test
	public void atc48LinearRingInPolygon() {
		//TODO: take a second look
		Assertions.assertNotNull(Utils.findField(LinearRing.class, "extrude"));
		Assertions.assertNotNull(Utils.findField(LinearRing.class, "tessellate"));
		Assertions.assertNotNull(Utils.findField(LinearRing.class, "altitudeMode"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc49Data()
	 */
	@Test
	public void atc49Data() {
		Assertions.assertNotNull(Utils.findField(Data.class, "displayName"));
		Assertions.assertNotNull(Utils.findField(Data.class, "value"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc50ResourceMapAlias()
	 */
	@Test
	public void atc50ResourceMapAlias() {
		Assertions.assertNotNull(Utils.findField(ResourceMap.class, "alias"));
		Assertions.assertNotNull(Utils.findField(Alias.class, "sourceHref"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc51LinkRefreshValues()
	 */
	@Test
	public void atc51LinkRefreshValues() {
		//TODO: take a second look
		Assertions.assertNotNull(Utils.findField(Link.class, "refreshInterval"));
		Assertions.assertNotNull(Utils.findField(Link.class, "viewRefreshTime"));
		Assertions.assertNotNull(Utils.findField(ViewRefreshMode.class, "ON_STOP"));
		Assertions.assertNotNull(Utils.findField(Icon.class, "refreshInterval"));
		Assertions.assertNotNull(Utils.findField(Icon.class, "viewRefreshTime"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc52PhotoOverlay()
	 */
	@Test
	public void atc52PhotoOverlay() {
		//TODO: take a second look
		Assertions.assertNotNull(Utils.findField(PhotoOverlay.class, "icon"));
		// The parameters are embedded within the URL; 
		// i.e, http://server.company.com/bigphoto/$[level]/row_$[x]_column_$[y].jpg. 
		// Check for the kml:ImagePyramid when the x, y, level parameters are present, 
		// or if the kml:ImagePyramid is present check for the x, y, level parameters.
		
		Icon icon = new Icon();
		icon.setHref("http://server.company.com/bigphoto/$[level]/row_$[x]_column_$[y].jpg");
		Assertions.assertEquals(icon.getHref(), "http://server.company.com/bigphoto/$[level]/row_$[x]_column_$[y].jpg");
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc53GroundOverlayMinimalContent()
	 */
	@Test
	public void atc53GroundOverlayMinimalContent() {
		// a kml:GroundOverlay element is not a descendant of kml:Update
		Assertions.assertNull(Utils.findClass(GroundOverlay.class, "Update"));
		
		Field latlonBoxGroundOverlay = Utils.findField(GroundOverlay.class, "latLonBox");
		Assertions.assertNotNull(latlonBoxGroundOverlay);
		Assertions.assertEquals(LatLonBox.class, latlonBoxGroundOverlay.getType());
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc54Camera()
	 */
	@Test
	public void atc54Camera() {
		//TODO: take a second look
		//TODO: if set to altitude is present the altitudeMode is not clmapToGround"
		
		Assertions.assertNull(Utils.findClass(Camera.class, "Update"));
		Assertions.assertNotNull(Utils.findField(Camera.class, "longitude"));
		Assertions.assertNotNull(Utils.findField(Camera.class, "latitude"));
		Assertions.assertNotNull(Utils.findField(Camera.class, "altitude"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc55Location()
	 */
	@Test
	public void atc55Location() {
		Assertions.assertNotNull(Utils.findField(Location.class, "longitude"));
		Assertions.assertNotNull(Utils.findField(Location.class, "latitude"));
		Assertions.assertNotNull(Utils.findField(Location.class, "altitude"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc56Overlay()
	 */
	@Test
	public void atc56Overlay() {
		Assertions.assertNull(Utils.findClass(Overlay.class, "Update"));
		Assertions.assertNull(Utils.findClass(ScreenOverlay.class, "Update"));
		Assertions.assertNull(Utils.findClass(PhotoOverlay.class, "Update"));
		Assertions.assertNull(Utils.findClass(GroundOverlay.class, "Update"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc57ScreenOverlay()
	 */
	@Test
	public void atc57ScreenOverlay() {
		Assertions.assertNull(Utils.findClass(ScreenOverlay.class, "Update"));
		Assertions.assertNotNull(Utils.findField(ScreenOverlay.class, "screenXY"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc58BaloonStyle()
	 */
	@Test
	public void atc58BaloonStyle() {
		Assertions.assertNull(Utils.findClass(BalloonStyle.class, "Update"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc59ExtendedData()
	 */
	@Test
	public void atc59ExtendedData() {
		Assertions.assertNotNull(Utils.findField(ExtendedData.class, "data"));
		Assertions.assertNotNull(Utils.findField(ExtendedData.class, "schemaData"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc60Folder()
	 */
	@Test
	public void atc60Folder() {
		Assertions.assertNull(Utils.findClass(Folder.class, "Update"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc61IconStyle()
	 */
	@Test
	public void atc61IconStyle() {
		Assertions.assertNull(Utils.findClass(IconStyle.class, "Update"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc62ImagePyramid()
	 */
	@Test
	public void atc62ImagePyramid() {
		Assertions.assertNull(Utils.findClass(ImagePyramid.class, "Update"));
		
		Assertions.assertNotNull(Utils.findField(ImagePyramid.class, "tileSize"));
		Assertions.assertNotNull(Utils.findField(ImagePyramid.class, "maxWidth"));
		Assertions.assertNotNull(Utils.findField(ImagePyramid.class, "maxHeight"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc63LabelStyle()
	 */
	@Test
	public void atc63LabelStyle() {
		Assertions.assertNull(Utils.findClass(LabelStyle.class, "Update"));
		
		Assertions.assertNotNull(Utils.findField(LabelStyle.class, "color"));
		Assertions.assertNotNull(Utils.findField(LabelStyle.class, "colorMode"));
		Assertions.assertNotNull(Utils.findField(LabelStyle.class, "scale"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc64ListStyle()
	 */
	@Test
	public void atc64ListStyle() {
		Assertions.assertNull(Utils.findClass(ListStyle.class, "Update"));

		Assertions.assertNotNull(Utils.findField(ListStyle.class, "listItemType"));
		Assertions.assertNotNull(Utils.findField(ListStyle.class, "bgColor"));
		Assertions.assertNotNull(Utils.findField(ListStyle.class, "itemIcon"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc65Sytle()
	 */
	@Test
	public void atc65Sytle() {
		Assertions.assertNull(Utils.findClass(Style.class, "Update"));
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc66MultiGeometry()
	 */
	@Test
	public void atc66MultiGeometry() {
		Assertions.assertNull(Utils.findClass(MultiGeometry.class, "Update"));
		
		Field geometryList = Utils.findField(MultiGeometry.class, "geometry");
		Assertions.assertNotNull(geometryList);
		Assertions.assertEquals(List.class, geometryList.getType());
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc67Placemark()
	 */
	@Test
	public void atc67Placemark() {
		Assertions.assertNull(Utils.findClass(Placemark.class, "Update"));
		
		Field geometry = Utils.findField(Placemark.class, "geometry");
		Assertions.assertNotNull(geometry);
		Assertions.assertEquals(Geometry.class, geometry.getType());
		
	}

	/**
	 * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc68StyleMap()
	 */
	@Test
	public void atc68StyleMap() {
		Assertions.assertNull(Utils.findClass(StyleMap.class, "Update"));
		
		// check if StyleMap contains pair
		Field pair = Utils.findField(StyleMap.class, "pair");
		Assertions.assertNotNull(pair);
		Assertions.assertEquals(List.class, pair.getType());
		Assertions.assertEquals(Pair.class, Utils.getClassForGenericList(pair.getGenericType().toString()));
		
		Assertions.assertNotNull(Utils.findField(StyleState.class, "NORMAL"));
		Assertions.assertNotNull(Utils.findField(StyleState.class, "HIGHLIGHT"));
	}

}
