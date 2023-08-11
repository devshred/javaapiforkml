package de.micromata.jak;

import java.util.List;

import org.junit.jupiter.api.Assertions;

import de.micromata.opengis.kml.v_2_2_0.Alias;
import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.BalloonStyle;
import de.micromata.opengis.kml.v_2_2_0.Camera;
import de.micromata.opengis.kml.v_2_2_0.Change;
import de.micromata.opengis.kml.v_2_2_0.ColorMode;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Create;
import de.micromata.opengis.kml.v_2_2_0.Delete;
import de.micromata.opengis.kml.v_2_2_0.DisplayMode;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.ExtendedData;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.GridOrigin;
import de.micromata.opengis.kml.v_2_2_0.GroundOverlay;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.ItemIconState;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LabelStyle;
import de.micromata.opengis.kml.v_2_2_0.LatLonAltBox;
import de.micromata.opengis.kml.v_2_2_0.LatLonBox;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.LineStyle;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.Link;
import de.micromata.opengis.kml.v_2_2_0.ListItemType;
import de.micromata.opengis.kml.v_2_2_0.ListStyle;
import de.micromata.opengis.kml.v_2_2_0.Location;
import de.micromata.opengis.kml.v_2_2_0.Lod;
import de.micromata.opengis.kml.v_2_2_0.LookAt;
import de.micromata.opengis.kml.v_2_2_0.Model;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.NetworkLink;
import de.micromata.opengis.kml.v_2_2_0.NetworkLinkControl;
import de.micromata.opengis.kml.v_2_2_0.Orientation;
import de.micromata.opengis.kml.v_2_2_0.Pair;
import de.micromata.opengis.kml.v_2_2_0.PhotoOverlay;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.PolyStyle;
import de.micromata.opengis.kml.v_2_2_0.Polygon;
import de.micromata.opengis.kml.v_2_2_0.RefreshMode;
import de.micromata.opengis.kml.v_2_2_0.Region;
import de.micromata.opengis.kml.v_2_2_0.Scale;
import de.micromata.opengis.kml.v_2_2_0.Schema;
import de.micromata.opengis.kml.v_2_2_0.ScreenOverlay;
import de.micromata.opengis.kml.v_2_2_0.Shape;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.StyleMap;
import de.micromata.opengis.kml.v_2_2_0.StyleState;
import de.micromata.opengis.kml.v_2_2_0.TimeSpan;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;
import de.micromata.opengis.kml.v_2_2_0.Units;
import de.micromata.opengis.kml.v_2_2_0.Update;
import de.micromata.opengis.kml.v_2_2_0.ViewRefreshMode;

public final class KmlReferenceCheck {

	public static void ballonStyle(final BalloonStyle balloonstyle) {
		Assertions.assertEquals("ID", balloonstyle.getId());
		Assertions.assertEquals("ffffffff", balloonstyle.getBgColor());
		Assertions.assertEquals("ff000000", balloonstyle.getTextColor());
		Assertions.assertEquals("...", balloonstyle.getText());
		Assertions.assertEquals(DisplayMode.DEFAULT, balloonstyle.getDisplayMode());
	}
	
	public static void ballonStyle(BalloonStyle balloonstyle, BalloonStyle marshalledAndBackAgain) {
		Assertions.assertEquals(balloonstyle, marshalledAndBackAgain);
  }
	
	public static void ballonStyleExample(final Kml kml) {
		Assertions.assertEquals("BalloonStyle.kml", kml.getFeature().getName());
		Assertions.assertTrue(kml.getFeature().isOpen(), "Document.isOpen(): ");
		Assertions.assertEquals("exampleBalloonStyle", kml.getFeature().getStyleSelector().get(0).getId());

		final BalloonStyle balloonstyle = ((Style) kml.getFeature().getStyleSelector().get(0)).getBalloonStyle();
		Assertions.assertEquals("ffffffbb", balloonstyle.getBgColor());
		Assertions
		    .assertEquals(
		        "<![CDATA[" + "<b><font color='#CC0000' size='+3'>$[name]</font></b>" + "<br/><br/>" + "<font face='Courier'>$[description]</font>" + "<br/><br/>" + "Extra text that will appear in the description balloon" + "<br/><br/>" + "<!-- insert the to/from hyperlinks -->" + "$[geDirections]]]>",
		        balloonstyle.getText());

		final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("BalloonStyle", placemark.getName());
		Assertions.assertEquals("An example of BalloonStyle", placemark.getDescription());
		Assertions.assertEquals("#exampleBalloonStyle", placemark.getStyleUrl());
		Assertions.assertEquals(new Coordinate(-122.370533, 37.823842, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
	}

	public static void camera(final Camera camera) {
		Assertions.assertEquals(0.0d, camera.getLongitude(), 0.00001d);
		Assertions.assertEquals(0.0d, camera.getLatitude(), 0.00001d);
		Assertions.assertEquals(0.0d, camera.getAltitude(), 0.00001d);
		Assertions.assertEquals(0.0d, camera.getHeading(), 0.00001d);
		Assertions.assertEquals(0.0d, camera.getTilt(), 0.00001d);
		Assertions.assertEquals(0.0d, camera.getRoll(), 0.00001d);
		Assertions.assertEquals(0.0d, camera.getHeading(), 0.00001d);
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, camera.getAltitudeMode());
	}

	@SuppressWarnings("deprecation")
  public static void document(final Document document) {
		Assertions.assertEquals("...", document.getName());
		Assertions.assertTrue(document.isVisibility());
		Assertions.assertFalse(document.isOpen());
		Assertions.assertEquals("...", document.getAtomAuthor().getNameOrUriOrEmail().get(0));
		Assertions.assertNotNull(document.getAtomLink());
		Assertions.assertEquals("...", document.getAddress());
		Assertions.assertNotNull(document.getXalAddressDetails());
		Assertions.assertEquals("...", document.getPhoneNumber());
		Assertions.assertEquals(2, document.getSnippet().getMaxLines());
		Assertions.assertEquals("...", document.getSnippet().getValue());
		Assertions.assertNotNull(document.getAbstractView());
		Assertions.assertNotNull(document.getTimePrimitive());
		Assertions.assertEquals("...", document.getStyleUrl());
		Assertions.assertNotNull(document.getStyleSelector());
		Assertions.assertNotNull(document.getRegion());
		Assertions.assertNotNull(document.getMetadata());
		Assertions.assertNotNull(document.getExtendedData());
	}

	public static void documentExample(final Kml kml) {
		Assertions.assertEquals("Document.kml", ((Document) kml.getFeature()).getName());
		Assertions.assertTrue(((Document) kml.getFeature()).isOpen());

		Assertions.assertEquals("exampleStyleDocument", kml.getFeature().getStyleSelector().get(0).getId());

		final LabelStyle labelstyle = ((Style) kml.getFeature().getStyleSelector().get(0)).getLabelStyle();
		Assertions.assertEquals("ff0000cc", labelstyle.getColor());

		final Placemark placemark1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("Document Feature 1", placemark1.getName());
		Assertions.assertEquals("#exampleStyleDocument", placemark1.getStyleUrl());
		Assertions.assertEquals(new Coordinate(-122.371, 37.816, 0.0), ((Point) placemark1.getGeometry()).getCoordinates().get(0));

		final Placemark placemark2 = (Placemark) ((Document) kml.getFeature()).getFeature().get(1);
		Assertions.assertEquals("Document Feature 2", placemark2.getName());
		Assertions.assertEquals("#exampleStyleDocument", placemark2.getStyleUrl());
		Assertions.assertEquals(new Coordinate(-122.370, 37.817, 0.0), ((Point) placemark2.getGeometry()).getCoordinates().get(0));
	}

	public static void extendedData(final ExtendedData extendedData) {
		Assertions.assertEquals("string", extendedData.getData().get(0).getName());
		Assertions.assertEquals("...", extendedData.getData().get(0).getDisplayName());
		Assertions.assertEquals("...", extendedData.getData().get(0).getValue());

		Assertions.assertEquals("anyURI", extendedData.getSchemaData().get(0).getSchemaUrl());
		Assertions.assertEquals("", extendedData.getSchemaData().get(0).getSimpleData().get(0).getName());
		Assertions.assertEquals("...", extendedData.getSchemaData().get(0).getSimpleData().get(0).getValue());
	}

	public static void extendedDataValue(final Placemark placemark) {
		Assertions.assertEquals("Club house", placemark.getName());
		Assertions.assertEquals("holeNumber", placemark.getExtendedData().getData().get(0).getName());
		Assertions.assertEquals("1", placemark.getExtendedData().getData().get(0).getValue());
		Assertions.assertEquals("holePar", placemark.getExtendedData().getData().get(1).getName());
		Assertions.assertEquals("4", placemark.getExtendedData().getData().get(1).getValue());
	}

	public static void extendedDataSimpleData(final Document document) {
		final Placemark p1 = (Placemark) document.getFeature().get(0);
		Assertions.assertEquals("Easy trail", p1.getName());
		Assertions.assertEquals("#TrailHeadTypeId", p1.getExtendedData().getSchemaData().get(0).getSchemaUrl());
		Assertions.assertEquals("TrailHeadName", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(0).getName());
		Assertions.assertEquals("Pi in the sky", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(0).getValue());
		Assertions.assertEquals("TrailLength", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(1).getName());
		Assertions.assertEquals("3.14159", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(1).getValue());
		Assertions.assertEquals("ElevationGain", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(2).getName());
		Assertions.assertEquals("10", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(2).getValue());
		Assertions.assertEquals(new Coordinate(-122.000, 37.002), ((Point) p1.getGeometry()).getCoordinates().get(0));

		final Placemark p2 = (Placemark) document.getFeature().get(1);
		Assertions.assertEquals("Difficult trail", p2.getName());
		Assertions.assertEquals("#TrailHeadTypeId", p2.getExtendedData().getSchemaData().get(0).getSchemaUrl());
		Assertions.assertEquals("TrailHeadName", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(0).getName());
		Assertions.assertEquals("Mount Everest", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(0).getValue());
		Assertions.assertEquals("TrailLength", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(1).getName());
		Assertions.assertEquals("347.45", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(1).getValue());
		Assertions.assertEquals("ElevationGain", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(2).getName());
		Assertions.assertEquals("10000", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(2).getValue());
		Assertions.assertEquals(new Coordinate(-122.000, 37.002), ((Point) p2.getGeometry()).getCoordinates().get(0));
	}

	public static void extendedDataNameSapcePrefix(final ExtendedData extendedData) {
		// FIXME:!!!
	}

	public static void featureAscriptionElement(final Kml kml) {
		Assertions.assertEquals("J. K. Rowling", kml.getFeature().getAtomAuthor().getNameOrUriOrEmail().get(0));
		Assertions.assertEquals("http://www.harrypotter.com", kml.getFeature().getAtomLink().getHref());

		final Placemark p1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("Hogwarts", p1.getName());
		Assertions.assertEquals(new Coordinate(1.0, 1.0, 0.0), ((Point) p1.getGeometry()).getCoordinates().get(0));

		final Placemark p2 = (Placemark) ((Document) kml.getFeature()).getFeature().get(1);
		Assertions.assertEquals("Little Hangleton", p2.getName());
		Assertions.assertEquals(new Coordinate(1.0, 2.0, 0.0), ((Point) p2.getGeometry()).getCoordinates().get(0));
	}

	@SuppressWarnings("deprecation")
  public static void folder(final Folder folder) {
		Assertions.assertEquals("ID", folder.getId());
		Assertions.assertEquals("...", folder.getName());
		Assertions.assertTrue(folder.isVisibility());
		Assertions.assertFalse(folder.isOpen());
		Assertions.assertEquals("...", folder.getAtomAuthor().getNameOrUriOrEmail().get(0));
		Assertions.assertNotNull(folder.getAtomLink());
		Assertions.assertEquals("...", folder.getAddress());
		Assertions.assertNotNull(folder.getXalAddressDetails());
		Assertions.assertEquals("...", folder.getPhoneNumber());
		Assertions.assertEquals(2, folder.getSnippet().getMaxLines());
		Assertions.assertEquals("...", folder.getSnippet().getValue());
		Assertions.assertNotNull(folder.getAbstractView());
		Assertions.assertNotNull(folder.getTimePrimitive());
		Assertions.assertEquals("...", folder.getStyleUrl());
		Assertions.assertNotNull(folder.getStyleSelector());
		Assertions.assertNotNull(folder.getRegion());
		Assertions.assertNotNull(folder.getMetadata());
		Assertions.assertNotNull(folder.getExtendedData());
	}

	public static void folderExample(final Kml kml) {
		final Folder folder = (Folder) kml.getFeature();
		Assertions.assertEquals("Folder.kml", folder.getName());
		Assertions.assertTrue(folder.isOpen());
		Assertions.assertEquals("A folder is a container that can hold multiple other objects", folder.getDescription());

		final Placemark p1 = (Placemark) folder.getFeature().get(0);
		Assertions.assertEquals("Folder object 1 (Placemark)", p1.getName());
		Assertions.assertEquals(new Coordinate(-122.377588, 37.830266, 0.0), ((Point) p1.getGeometry()).getCoordinates().get(0));

		final Placemark p2 = (Placemark) folder.getFeature().get(1);
		Assertions.assertEquals("Folder object 2 (Polygon)", p2.getName());
		Assertions.assertEquals(new Coordinate("-122.377830,37.830445,0.0"), ((Polygon) p2.getGeometry()).getOuterBoundaryIs().getLinearRing().getCoordinates()
		    .get(0));
		Assertions.assertEquals(new Coordinate("-122.377576,37.830631,0.0"), ((Polygon) p2.getGeometry()).getOuterBoundaryIs().getLinearRing().getCoordinates()
		    .get(1));
		Assertions.assertEquals(new Coordinate("-122.377840,37.830642,0.0"), ((Polygon) p2.getGeometry()).getOuterBoundaryIs().getLinearRing().getCoordinates()
		    .get(2));
		Assertions.assertEquals(new Coordinate("-122.377830,37.830445,0.0"), ((Polygon) p2.getGeometry()).getOuterBoundaryIs().getLinearRing().getCoordinates()
		    .get(3));

		final Placemark p3 = (Placemark) folder.getFeature().get(2);
		Assertions.assertEquals("Folder object 3 (Path)", p3.getName());
		Assertions.assertEquals(new Coordinate(-122.378009, 37.830128, 0.0), ((LineString) p3.getGeometry()).getCoordinates().get(0));
		Assertions.assertEquals(new Coordinate(-122.377885, 37.830379, 0.0), ((LineString) p3.getGeometry()).getCoordinates().get(1));
	}

	@SuppressWarnings("deprecation")
  public static void groundoverlay(final GroundOverlay groundoverlay) {
		// <!-- inherited from Feature element -->
		Assertions.assertEquals("ID", groundoverlay.getId());
		Assertions.assertEquals("...", groundoverlay.getName());
		Assertions.assertTrue(groundoverlay.isVisibility());
		Assertions.assertFalse(groundoverlay.isOpen());
		Assertions.assertEquals("...", groundoverlay.getAtomAuthor().getNameOrUriOrEmail().get(0));
		Assertions.assertNotNull(groundoverlay.getAtomLink());
		Assertions.assertEquals("...", groundoverlay.getAddress());
		Assertions.assertNotNull(groundoverlay.getXalAddressDetails());
		Assertions.assertEquals("...", groundoverlay.getPhoneNumber());
		Assertions.assertEquals(2, groundoverlay.getSnippet().getMaxLines());
		Assertions.assertEquals("...", groundoverlay.getSnippet().getValue());
		Assertions.assertNotNull(groundoverlay.getAbstractView());
		Assertions.assertNotNull(groundoverlay.getTimePrimitive());
		Assertions.assertEquals("...", groundoverlay.getStyleUrl());
		Assertions.assertNotNull(groundoverlay.getStyleSelector());
		Assertions.assertNotNull(groundoverlay.getRegion());
		Assertions.assertNotNull(groundoverlay.getMetadata());
		Assertions.assertNotNull(groundoverlay.getExtendedData());

		// <!-- inherited from Overlay element -->
		Assertions.assertEquals("ffffffff", groundoverlay.getColor());
		Assertions.assertEquals(0, groundoverlay.getDrawOrder());
		Assertions.assertEquals("...", groundoverlay.getIcon().getHref());

		// <!-- specific to GroundOverlay -->
		Assertions.assertEquals(0.0d, groundoverlay.getAltitude(), 0.0001d);
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, groundoverlay.getAltitudeMode());
		Assertions.assertEquals(0.0d, groundoverlay.getLatLonBox().getNorth(), 0.0001d);
		Assertions.assertEquals(0.0d, groundoverlay.getLatLonBox().getSouth(), 0.0001d);
		Assertions.assertEquals(0.0d, groundoverlay.getLatLonBox().getEast(), 0.0001d);
		Assertions.assertEquals(0.0d, groundoverlay.getLatLonBox().getWest(), 0.0001d);
		Assertions.assertEquals(0.0d, groundoverlay.getLatLonBox().getRotation(), 0.0001d);
	}

	public static void groundOverlayLatLonBox(final LatLonBox latlonBox) {
		Assertions.assertEquals(48.25475939255556d, latlonBox.getNorth(), 0.0001d);
		Assertions.assertEquals(48.25207367852141d, latlonBox.getSouth(), 0.0001d);
		Assertions.assertEquals(-90.86591508839973d, latlonBox.getEast(), 0.0001d);
		Assertions.assertEquals(-90.8714285289695d, latlonBox.getWest(), 0.0001d);
		Assertions.assertEquals(39.37878630116985d, latlonBox.getRotation(), 0.0001d);
	}

	public static void groundOverlayExample(final Kml kml) {
		final GroundOverlay groundoverlay = (GroundOverlay) kml.getFeature();
		Assertions.assertEquals("GroundOverlay.kml", groundoverlay.getName());
		Assertions.assertEquals("7fffffff", groundoverlay.getColor());
		Assertions.assertEquals(1, groundoverlay.getDrawOrder());

		Assertions.assertEquals("http://www.google.com/intl/en/images/logo.gif", groundoverlay.getIcon().getHref());
		Assertions.assertEquals(RefreshMode.ON_INTERVAL, groundoverlay.getIcon().getRefreshMode());
		Assertions.assertEquals(86400d, groundoverlay.getIcon().getRefreshInterval(), 0.0001);
		Assertions.assertEquals(0.75d, groundoverlay.getIcon().getViewBoundScale(), 0.0001);

		Assertions.assertEquals(37.83234d, groundoverlay.getLatLonBox().getNorth(), 0.0001d);
		Assertions.assertEquals(37.832122d, groundoverlay.getLatLonBox().getSouth(), 0.0001d);
		Assertions.assertEquals(-122.373033d, groundoverlay.getLatLonBox().getEast(), 0.0001d);
		Assertions.assertEquals(-122.373724d, groundoverlay.getLatLonBox().getWest(), 0.0001d);
		Assertions.assertEquals(45.0d, groundoverlay.getLatLonBox().getRotation(), 0.0001d);
	}

	public static void icon(final Icon icon) {
		Assertions.assertEquals("...", icon.getHref());
		Assertions.assertEquals(RefreshMode.ON_CHANGE, icon.getRefreshMode());
		Assertions.assertEquals(4d, icon.getRefreshInterval(), 0.0001);
		Assertions.assertEquals(ViewRefreshMode.NEVER, icon.getViewRefreshMode());
		Assertions.assertEquals(4d, icon.getViewRefreshTime(), 0.0001);
		Assertions.assertEquals(1d, icon.getViewBoundScale(), 0.0001);
		Assertions.assertEquals("...", icon.getHttpQuery());
	}

	public static void iconStyle(final IconStyle iconstyle) {
		Assertions.assertEquals("ffffffff", iconstyle.getColor());
		Assertions.assertEquals(ColorMode.NORMAL, iconstyle.getColorMode());

		Assertions.assertEquals(1d, iconstyle.getScale(), 0.0001);
		Assertions.assertEquals(0, iconstyle.getHeading(), 0.0001);
		Assertions.assertEquals("...", iconstyle.getIcon().getHref());

		Assertions.assertEquals(0.5d, iconstyle.getHotSpot().getX(), 0.0001);
		Assertions.assertEquals(0.5d, iconstyle.getHotSpot().getY(), 0.0001);
		Assertions.assertEquals(Units.FRACTION, iconstyle.getHotSpot().getXunits());
		Assertions.assertEquals(Units.FRACTION, iconstyle.getHotSpot().getYunits());
	}

	public static void iconStyleExample(final Kml kml) {
		final Style style = (Style) ((Document) kml.getFeature()).getStyleSelector().get(0);
		Assertions.assertEquals("randomColorIcon", style.getId());
		Assertions.assertEquals("ff00ff00", style.getIconStyle().getColor());
		Assertions.assertEquals(ColorMode.RANDOM, style.getIconStyle().getColorMode());
		Assertions.assertEquals(1.1d, style.getIconStyle().getScale(), 0.0001);
		Assertions.assertEquals("http://maps.google.com/mapfiles/kml/pal3/icon21.png", style.getIconStyle().getIcon().getHref());

		final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("IconStyle.kml", placemark.getName());
		Assertions.assertEquals("#randomColorIcon", placemark.getStyleUrl());
		Assertions.assertEquals(new Coordinate(-122.36868, 37.831145, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
	}

	public static void kml(final Kml kml) {
		Assertions.assertEquals("sky", kml.getHint());
	}

	public static void labelStyle(final LabelStyle labelstyle) {
		// <!-- inherited from ColorStyle -->
		Assertions.assertEquals("ffffffff", labelstyle.getColor());
		Assertions.assertEquals(ColorMode.NORMAL, labelstyle.getColorMode());

		// <!-- specific to LabelStyle -->
		Assertions.assertEquals(1d, labelstyle.getScale(), 0.0001);
	}

	public static void labelStyleExample(final Kml kml) {
		final Style style = (Style) ((Document) kml.getFeature()).getStyleSelector().get(0);
		Assertions.assertEquals("randomLabelColor", style.getId());
		Assertions.assertEquals("ff0000cc", style.getLabelStyle().getColor());
		Assertions.assertEquals(ColorMode.RANDOM, style.getLabelStyle().getColorMode());
		Assertions.assertEquals(1.5d, style.getLabelStyle().getScale(), 0.0001);

		final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("LabelStyle.kml", placemark.getName());
		Assertions.assertEquals("#randomLabelColor", placemark.getStyleUrl());
		Assertions.assertEquals(new Coordinate(-122.367375, 37.829192, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
	}

	public static void linearRing(final LinearRing linearring) {
		Assertions.assertEquals("ID", linearring.getId());

		// <!-- specific to LinearRing -->
		Assertions.assertTrue(linearring.isExtrude());
		Assertions.assertTrue(linearring.isTessellate());
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, linearring.getAltitudeMode());
		Assertions.assertEquals(new Coordinate(0.0, 0.0, 0.0), linearring.getCoordinates().get(0));
	}

	public static void linearRingExample(final Kml kml) {
		final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("LinearRing.kml", placemark.getName());
		Assertions.assertEquals(new Coordinate("-122.365662,37.826988,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(0));
		Assertions.assertEquals(new Coordinate("-122.365202,37.826302,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(1));
		Assertions.assertEquals(new Coordinate("-122.364581,37.82655,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(2));
		Assertions.assertEquals(new Coordinate("-122.365038,37.827237,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(3));
		Assertions.assertEquals(new Coordinate("-122.365662,37.826988,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(4));
	}

	public static void lineString(final LineString linestring) {
		Assertions.assertEquals("ID", linestring.getId());

		// <!-- specific to LineString -->
		Assertions.assertFalse(linestring.isExtrude());
		Assertions.assertFalse(linestring.isTessellate());
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, linestring.getAltitudeMode());
		Assertions.assertEquals(new Coordinate(0.0, 0.0, 0.0), linestring.getCoordinates().get(0));
	}

	public static void lineStringExample(final Kml kml) {
		Assertions.assertEquals("LineString.kml", kml.getFeature().getName());
		Assertions.assertTrue(kml.getFeature().isOpen());

		final LookAt lookat = ((LookAt) kml.getFeature().getAbstractView());
		Assertions.assertEquals(-122.36415, lookat.getLongitude(), 0.0001);
		Assertions.assertEquals(37.824553, lookat.getLatitude(), 0.0001);
		Assertions.assertEquals(50.0, lookat.getTilt(), 0.0001);
		Assertions.assertEquals(150.0, lookat.getRange(), 0.0001);
		Assertions.assertEquals(0.0, lookat.getHeading(), 0.0001);

		final Placemark p1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("unextruded", p1.getName());
		Assertions.assertFalse(((LineString) p1.getGeometry()).isExtrude());
		Assertions.assertTrue(((LineString) p1.getGeometry()).isTessellate());
		Assertions.assertEquals(new Coordinate(-122.364383, 37.824664, 0.0), ((LineString) p1.getGeometry()).getCoordinates().get(0));
		Assertions.assertEquals(new Coordinate(-122.364152, 37.824322, 0.0), ((LineString) p1.getGeometry()).getCoordinates().get(1));

		final Placemark p2 = (Placemark) ((Document) kml.getFeature()).getFeature().get(1);
		Assertions.assertEquals("extruded", p2.getName());
		Assertions.assertTrue(((LineString) p2.getGeometry()).isExtrude());
		Assertions.assertTrue(((LineString) p2.getGeometry()).isTessellate());
		Assertions.assertEquals(new Coordinate(-122.364167, 37.824787, 50), ((LineString) p2.getGeometry()).getCoordinates().get(0));
		Assertions.assertEquals(new Coordinate(-122.363917, 37.824423, 50), ((LineString) p2.getGeometry()).getCoordinates().get(1));

	}

	public static void lineStyle(final LineStyle linestyle) {
		Assertions.assertEquals("ID", linestyle.getId());

		// <!-- inherited from ColorStyle -->
		Assertions.assertEquals("ffffffff", linestyle.getColor());
		Assertions.assertEquals(ColorMode.NORMAL, linestyle.getColorMode());

		// <!-- specific to LineStyle -->
		Assertions.assertEquals(1.0, linestyle.getWidth(), 0.0001);

	}

	public static void lineStyleExample(final Kml kml) {
		Assertions.assertEquals("LineStyle.kml", kml.getFeature().getName());
		Assertions.assertTrue(kml.getFeature().isOpen());

		final Style style = (Style) ((Document) kml.getFeature()).getStyleSelector().get(0);
		Assertions.assertEquals("linestyleExample", style.getId());
		Assertions.assertEquals("7f0000ff", style.getLineStyle().getColor());
		Assertions.assertEquals(4.0d, style.getLineStyle().getWidth(), 0.0001);

		final Placemark p1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("LineStyle Example", p1.getName());
		Assertions.assertEquals("#linestyleExample", p1.getStyleUrl());
		Assertions.assertTrue(((LineString) p1.getGeometry()).isExtrude());
		Assertions.assertTrue(((LineString) p1.getGeometry()).isTessellate());
		Assertions.assertEquals(new Coordinate(-122.364383, 37.824664, 0.0), ((LineString) p1.getGeometry()).getCoordinates().get(0));
		Assertions.assertEquals(new Coordinate(-122.364152, 37.824322, 0.0), ((LineString) p1.getGeometry()).getCoordinates().get(1));
	}

	public static void link(final Link link) {
		Assertions.assertEquals("ID", link.getId());
		Assertions.assertEquals("...", link.getHref());
		Assertions.assertEquals(RefreshMode.ON_CHANGE, link.getRefreshMode());
		Assertions.assertEquals(4.0d, link.getRefreshInterval(), 0.0001);
		Assertions.assertEquals(ViewRefreshMode.NEVER, link.getViewRefreshMode());
		Assertions.assertEquals(4.0d, link.getViewRefreshTime(), 0.0001);
		Assertions.assertEquals(1.0d, link.getViewBoundScale(), 0.0001);
		Assertions.assertEquals("BBOX=bboxWest", link.getViewFormat());
		Assertions.assertEquals("...", link.getHttpQuery());
	}

	public static void linkExample(final NetworkLink networklink) {
		Assertions.assertEquals("NE US Radar", networklink.getName());
		Assertions.assertTrue(networklink.isFlyToView());
		final Link link = networklink.getLink();
		Assertions.assertEquals("http://www.example.com/geotiff/NE/MergedReflectivityQComposite.kml", link.getHref());
		Assertions.assertEquals(RefreshMode.ON_INTERVAL, link.getRefreshMode());
		Assertions.assertEquals(30.0d, link.getRefreshInterval(), 0.0001);
		Assertions.assertEquals(ViewRefreshMode.ON_STOP, link.getViewRefreshMode());
		Assertions.assertEquals(7.0d, link.getViewRefreshTime(), 0.0001);
		Assertions.assertEquals("BBOX=bboxWest", link.getViewFormat());
	}

	public static void listStyle(final ListStyle liststyle) {
		Assertions.assertEquals("ID", liststyle.getId());
		Assertions.assertEquals(ListItemType.CHECK, liststyle.getListItemType());
		Assertions.assertEquals("ffffffff", liststyle.getBgColor());
		Assertions.assertEquals(ItemIconState.OPEN, liststyle.getItemIcon().get(0).getState().get(0));
		Assertions.assertEquals("...", liststyle.getItemIcon().get(0).getHref());

	}

	public static void lookAt(final LookAt lookat) {
		Assertions.assertEquals(0.0, lookat.getLongitude(), 0.0001);
		Assertions.assertEquals(0.0, lookat.getLatitude(), 0.0001);
		Assertions.assertEquals(0.0, lookat.getRange(), 0.0001);
		Assertions.assertEquals(0.0, lookat.getTilt(), 0.0001);
		Assertions.assertEquals(0.0, lookat.getHeading(), 0.0001);
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, lookat.getAltitudeMode());
	}

	public static void lookAtExample(final Kml kml) {
		Assertions.assertEquals("LookAt.kml", kml.getFeature().getName());

		final LookAt lookat = ((LookAt) kml.getFeature().getAbstractView());
		// FIXME: <gx:TimeStamp>
		// FIXME: <when>1994</when>
		// FIXME: </gx:TimeStamp>

		Assertions.assertEquals(-122.363, lookat.getLongitude(), 0.0001);
		Assertions.assertEquals(37.81, lookat.getLatitude(), 0.0001);
		Assertions.assertEquals(2000.0, lookat.getAltitude(), 0.0001);
		Assertions.assertEquals(500.0, lookat.getRange(), 0.0001);
		Assertions.assertEquals(45.0, lookat.getTilt(), 0.0001);
		Assertions.assertEquals(0.0, lookat.getHeading(), 0.0001);
		Assertions.assertEquals(AltitudeMode.RELATIVE_TO_GROUND, lookat.getAltitudeMode());

		final Placemark placemark = (Placemark) kml.getFeature();
		Assertions.assertEquals(new Coordinate(-122.363, 37.82, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
	}

	public static void model(final Model model) {
		Assertions.assertEquals("ID", model.getId());
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, model.getAltitudeMode());

		Assertions.assertEquals(0.0, model.getLocation().getLongitude(), 0.0001);
		Assertions.assertEquals(0.0, model.getLocation().getLatitude(), 0.0001);
		Assertions.assertEquals(0.0, model.getLocation().getAltitude(), 0.0001);

		Assertions.assertEquals(0.0, model.getOrientation().getHeading(), 0.0001);
		Assertions.assertEquals(0.0, model.getOrientation().getTilt(), 0.0001);
		Assertions.assertEquals(0.0, model.getOrientation().getRoll(), 0.0001);

		Assertions.assertEquals(1.0, model.getScale().getX(), 0.0001);
		Assertions.assertEquals(1.0, model.getScale().getY(), 0.0001);
		Assertions.assertEquals(1.0, model.getScale().getZ(), 0.0001);

		Assertions.assertEquals("...", model.getLink().getHref());

		Assertions.assertEquals("...", model.getResourceMap().getAlias().get(0).getTargetHref());
		Assertions.assertEquals("...", model.getResourceMap().getAlias().get(0).getSourceHref());

	}

	public static void modelLocation(final Location location) {
		Assertions.assertEquals(39.55375305703105, location.getLongitude(), 0.0001);
		Assertions.assertEquals(-118.9813220168456, location.getLatitude(), 0.0001);
		Assertions.assertEquals(1223.0, location.getAltitude(), 0.0001);

	}

	public static void modelOrientation(final Orientation orientation) {
		Assertions.assertEquals(45.0, orientation.getHeading(), 0.0001);
		Assertions.assertEquals(10.0, orientation.getTilt(), 0.0001);
		Assertions.assertEquals(0.0, orientation.getRoll(), 0.0001);

	}

	public static void modelScale(final Scale scale) {
		Assertions.assertEquals(2.5, scale.getX(), 0.0001);
		Assertions.assertEquals(2.5, scale.getY(), 0.0001);
		Assertions.assertEquals(3.5, scale.getZ(), 0.0001);

	}

	public static void modelResourceMap(final Alias alias) {
		Assertions.assertEquals("../images/foo.jpg", alias.getTargetHref());
		Assertions.assertEquals("c:\\mytextures\\foo.jpg", alias.getSourceHref());
	}

	public static void modelExample(final Model model) {
		Assertions.assertEquals("khModel543", model.getId());
		Assertions.assertEquals(AltitudeMode.RELATIVE_TO_GROUND, model.getAltitudeMode());

		Assertions.assertEquals(39.55375305703105, model.getLocation().getLongitude(), 0.0001);
		Assertions.assertEquals(-118.9813220168456, model.getLocation().getLatitude(), 0.0001);
		Assertions.assertEquals(1223.0, model.getLocation().getAltitude(), 0.0001);

		Assertions.assertEquals(45.0, model.getOrientation().getHeading(), 0.0001);
		Assertions.assertEquals(10.0, model.getOrientation().getTilt(), 0.0001);
		Assertions.assertEquals(0.0, model.getOrientation().getRoll(), 0.0001);

		Assertions.assertEquals(1.0, model.getScale().getX(), 0.0001);
		Assertions.assertEquals(1.0, model.getScale().getY(), 0.0001);
		Assertions.assertEquals(1.0, model.getScale().getZ(), 0.0001);

		Assertions.assertEquals("house.dae", model.getLink().getHref());
		// FIXME: TODO <refreshMode>once</refreshMode>
		Assertions.assertEquals(RefreshMode.ON_CHANGE, model.getLink().getRefreshMode());

		Assertions.assertEquals("../files/CU-Macky---Center-StairsnoCulling.jpg", model.getResourceMap().getAlias().get(0).getTargetHref());
		Assertions.assertEquals("CU-Macky---Center-StairsnoCulling.jpg", model.getResourceMap().getAlias().get(0).getSourceHref());

		Assertions.assertEquals("../files/CU-Macky-4sideturretnoCulling.jpg", model.getResourceMap().getAlias().get(1).getTargetHref());
		Assertions.assertEquals("CU-Macky-4sideturretnoCulling.jpg", model.getResourceMap().getAlias().get(1).getSourceHref());

		Assertions.assertEquals("../files/CU-Macky-Back-NorthnoCulling.jpg", model.getResourceMap().getAlias().get(2).getTargetHref());
		Assertions.assertEquals("CU-Macky-Back-NorthnoCulling.jpg", model.getResourceMap().getAlias().get(2).getSourceHref());
	}

	public static void multiGeometry(final MultiGeometry multigeometry) {
		Assertions.assertEquals("ID", multigeometry.getId());
	}

	public static void multiGeometryExample(final Placemark placemark) {
		Assertions.assertEquals("SF Marina Harbor Master", placemark.getName());
		Assertions.assertFalse(placemark.isVisibility());
		final List<Geometry> multigeometry = ((MultiGeometry) placemark.getGeometry()).getGeometry();
		Assertions.assertEquals(new Coordinate(-122.4425587930444, 37.80666418607323, 0.0), ((LineString) multigeometry.get(0)).getCoordinates()
		    .get(0));
		Assertions.assertEquals(new Coordinate(-122.4428379594768, 37.80663578323093, 0.0), ((LineString) multigeometry.get(0)).getCoordinates()
		    .get(1));
		Assertions.assertEquals(new Coordinate(-122.4425509770566, 37.80662588061205, 0.0), ((LineString) multigeometry.get(1)).getCoordinates()
		    .get(0));
		Assertions.assertEquals(new Coordinate(-122.4428340530617, 37.8065999493009, 0.0), ((LineString) multigeometry.get(1)).getCoordinates()
		    .get(1));
	}

	@SuppressWarnings("deprecation")
  public static void networkLink(final NetworkLink networklink) {
		Assertions.assertEquals("ID", networklink.getId());
		// <!-- inherited from Feature element -->
		Assertions.assertEquals("...", networklink.getName());
		Assertions.assertTrue(networklink.isVisibility());
		Assertions.assertFalse(networklink.isOpen());
		Assertions.assertEquals("...", networklink.getAtomAuthor().getNameOrUriOrEmail().get(0));
		Assertions.assertNotNull(networklink.getAtomLink());
		Assertions.assertEquals("...", networklink.getAddress());
		Assertions.assertNotNull(networklink.getXalAddressDetails());
		Assertions.assertEquals("...", networklink.getPhoneNumber());
		Assertions.assertEquals(2, networklink.getSnippet().getMaxLines());
		Assertions.assertEquals("...", networklink.getSnippet().getValue());
		Assertions.assertNotNull(networklink.getAbstractView());
		Assertions.assertNotNull(networklink.getTimePrimitive());
		Assertions.assertEquals("...", networklink.getStyleUrl());
		Assertions.assertNotNull(networklink.getStyleSelector());
		Assertions.assertNotNull(networklink.getRegion());
		Assertions.assertNotNull(networklink.getMetadata());
		Assertions.assertNotNull(networklink.getExtendedData());

		// <!-- specific to NetworkLink -->
		Assertions.assertFalse(networklink.isRefreshVisibility());
		Assertions.assertFalse(networklink.isFlyToView());
		Assertions.assertEquals("...", networklink.getLink().getHref());
	}

	public static void networkLinkExample(final Document document) {
		Assertions.assertTrue(document.isVisibility());
		final NetworkLink networklink = (NetworkLink) document.getFeature().get(0);
		Assertions.assertEquals("NE US Radar", networklink.getName());
		Assertions.assertTrue(networklink.isRefreshVisibility());
		Assertions.assertTrue(networklink.isFlyToView());
		Assertions.assertEquals("...", networklink.getLink().getHref());
	}

	@SuppressWarnings("deprecation")
	public static void networkLinkControl(final NetworkLinkControl networklinkcontrol) {
		Assertions.assertEquals(0.0, networklinkcontrol.getMinRefreshPeriod(), 0.0001);
		Assertions.assertEquals(-1.0, networklinkcontrol.getMaxSessionLength(), 0.0001);
		Assertions.assertEquals("...", networklinkcontrol.getCookie());
		Assertions.assertEquals("...", networklinkcontrol.getMessage());
		Assertions.assertEquals("...", networklinkcontrol.getLinkName());
		Assertions.assertEquals("...", networklinkcontrol.getLinkDescription());
		Assertions.assertEquals(2, networklinkcontrol.getLinkSnippet().getMaxLines());
		Assertions.assertEquals("...", networklinkcontrol.getLinkSnippet().getValue());
		Assertions.assertEquals("...", networklinkcontrol.getExpires());
		Assertions.assertNotNull(networklinkcontrol.getUpdate());
		Assertions.assertNotNull(networklinkcontrol.getAbstractView());

	}

	public static void networkLinkControlExample(final Kml kml) {
		Assertions.assertEquals("This is a pop-up message. You will only see this once", kml.getNetworkLinkControl().getMessage());
		Assertions.assertEquals("cookie=sometext", kml.getNetworkLinkControl().getCookie());
		Assertions.assertEquals("New KML features", kml.getNetworkLinkControl().getLinkName());
		Assertions.assertEquals("<![CDATA[KML now has new features available!]]>", kml.getNetworkLinkControl().getLinkDescription());

	}

	public static void overlayIcon(final Icon icon) {
		Assertions.assertEquals("icon.jpg", icon.getHref());
	}

	@SuppressWarnings("deprecation")
	public static void photoOverlay(final PhotoOverlay photooverlay) {
		// <!-- inherited from Feature element -->
		Assertions.assertEquals("...", photooverlay.getName());
		Assertions.assertTrue(photooverlay.isVisibility());
		Assertions.assertFalse(photooverlay.isOpen());
		Assertions.assertNotNull(photooverlay.getAtomAuthor());
		Assertions.assertNotNull(photooverlay.getAtomLink());
		Assertions.assertEquals("...", photooverlay.getAddress());
		Assertions.assertNotNull(photooverlay.getXalAddressDetails());
		Assertions.assertEquals("...", photooverlay.getPhoneNumber());
		Assertions.assertEquals(2, photooverlay.getSnippet().getMaxLines());
		Assertions.assertEquals("...", photooverlay.getSnippet().getValue());
		Assertions.assertNotNull(photooverlay.getAbstractView());
		Assertions.assertNotNull(photooverlay.getTimePrimitive());
		Assertions.assertEquals("...", photooverlay.getStyleUrl());
		Assertions.assertNotNull(photooverlay.getStyleSelector());
		Assertions.assertNotNull(photooverlay.getRegion());
		Assertions.assertNotNull(photooverlay.getMetadata());
		Assertions.assertNotNull(photooverlay.getExtendedData());

		// <!-- inherited from Overlay element -->
		Assertions.assertEquals("ffffffff", photooverlay.getColor());
		Assertions.assertEquals(0, photooverlay.getDrawOrder());
		Assertions.assertEquals("...", photooverlay.getIcon().getHref());

		// <!-- specific to PhotoOverlay -->
		Assertions.assertEquals(0, photooverlay.getRotation(), 0.0001);
		Assertions.assertEquals(0.0, photooverlay.getViewVolume().getLeftFov(), 0.0001);
		Assertions.assertEquals(0.0, photooverlay.getViewVolume().getRightFov(), 0.0001);
		Assertions.assertEquals(0.0, photooverlay.getViewVolume().getBottomFov(), 0.0001);
		Assertions.assertEquals(0.0, photooverlay.getViewVolume().getTopFov(), 0.0001);
		Assertions.assertEquals(0.0, photooverlay.getViewVolume().getNear(), 0.0001);

		Assertions.assertEquals(0, photooverlay.getImagePyramid().getTileSize());
		Assertions.assertEquals(0, photooverlay.getImagePyramid().getMaxWidth());
		Assertions.assertEquals(0, photooverlay.getImagePyramid().getMaxHeight());
		Assertions.assertEquals(GridOrigin.LOWER_LEFT, photooverlay.getImagePyramid().getGridOrigin());
		Assertions.assertEquals(new Coordinate(0.0, 0.0, 0.0), photooverlay.getPoint().getCoordinates().get(0));
		Assertions.assertEquals(Shape.RECTANGLE, photooverlay.getShape());
	}

	public static void photoOverlayExample(final PhotoOverlay photooverlay) {
		// <!-- inherited from Feature element -->
		Assertions.assertEquals("A simple non-pyramidal photo", photooverlay.getName());
		Assertions.assertEquals("High above the ocean", photooverlay.getDescription());

		// <!-- Overlay elements -->
		// <!-- A simple normal jpeg image -->
		Assertions.assertEquals("small-photo.jpg", photooverlay.getIcon().getHref());

		// <!-- PhotoOverlay elements -->
		// <!-- default: <rotation> default is 0 -->
		Assertions.assertEquals(0, photooverlay.getRotation(), 0.0001);
		Assertions.assertEquals(1000.0, photooverlay.getViewVolume().getNear(), 0.0001);
		Assertions.assertEquals(-60.0, photooverlay.getViewVolume().getLeftFov(), 0.0001);
		Assertions.assertEquals(60.0, photooverlay.getViewVolume().getRightFov(), 0.0001);
		Assertions.assertEquals(-45.0, photooverlay.getViewVolume().getBottomFov(), 0.0001);
		Assertions.assertEquals(45.0, photooverlay.getViewVolume().getTopFov(), 0.0001);

		Assertions.assertEquals(new Coordinate(1.0, 1.0, 0.0), photooverlay.getPoint().getCoordinates().get(0));
		// <!-- default: <shape> -->
		Assertions.assertEquals(Shape.RECTANGLE, photooverlay.getShape());
	}

	@SuppressWarnings("deprecation")
	public static void placemark(final Placemark placemark) {
		// <!-- inherited from Feature element -->
		Assertions.assertEquals("...", placemark.getName());
		Assertions.assertTrue(placemark.isVisibility());
		Assertions.assertFalse(placemark.isOpen());
		Assertions.assertNotNull(placemark.getAtomAuthor());
		Assertions.assertNotNull(placemark.getAtomLink());
		Assertions.assertEquals("...", placemark.getAddress());
		Assertions.assertNotNull(placemark.getXalAddressDetails());
		Assertions.assertEquals("...", placemark.getPhoneNumber());
		Assertions.assertEquals(2, placemark.getSnippet().getMaxLines());
		Assertions.assertEquals("...", placemark.getSnippet().getValue());
		Assertions.assertNotNull(placemark.getAbstractView());
		Assertions.assertNotNull(placemark.getTimePrimitive());
		Assertions.assertEquals("...", placemark.getStyleUrl());
		Assertions.assertNotNull(placemark.getStyleSelector());
		Assertions.assertNotNull(placemark.getRegion());
		Assertions.assertNotNull(placemark.getMetadata());
		Assertions.assertNotNull(placemark.getExtendedData());

		// <!-- specific to Placemark element -->
		Assertions.assertNotNull(placemark.getGeometry());
	}

	public static void placemarkExample(final Placemark placemark) {
		Assertions.assertEquals("Google Earth - New Placemark", placemark.getName());
		Assertions.assertEquals("Some Descriptive text.", placemark.getDescription());

		final LookAt lookat = (LookAt) placemark.getAbstractView();
		Assertions.assertEquals(-90.86879847669974, lookat.getLongitude(), 0.0001);
		Assertions.assertEquals(48.25330383601299, lookat.getLatitude(), 0.0001);
		Assertions.assertEquals(440.8, lookat.getRange(), 0.0001);
		Assertions.assertEquals(8.3, lookat.getTilt(), 0.0001);
		Assertions.assertEquals(2.7, lookat.getHeading(), 0.0001);

		Assertions.assertEquals(new Coordinate(-90.86948943473118, 48.25450093195546, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(
		    0));
	}

	public static void point(final Point point) {
		Assertions.assertEquals("ID", point.getId());

		// <!-- specific to Point -->
		Assertions.assertFalse(point.isExtrude());
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, point.getAltitudeMode());

		Assertions.assertEquals(new Coordinate(0.0, 0.0, 0.0), point.getCoordinates().get(0));
	}

	public static void pointExample(final Point point) {
		Assertions.assertEquals(new Coordinate(-90.86948943473118, 48.25450093195546), point.getCoordinates().get(0));
	}

	public static void polygon(final Polygon polygon) {
		Assertions.assertEquals("ID", polygon.getId());

		// <!-- specific to Polygon -->
		Assertions.assertFalse(polygon.isExtrude());
		Assertions.assertFalse(polygon.isTessellate());
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, polygon.getAltitudeMode());

		Assertions.assertEquals(new Coordinate(0.0, 0.0, 0.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(0));
		Assertions.assertEquals(new Coordinate(0.0, 0.0, 0.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates().get(0));

	}

	public static void polygonExample(final Kml kml) {
		Assertions.assertEquals("Polygon.kml", kml.getFeature().getName());
		Assertions.assertFalse(kml.getFeature().isOpen());

		final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("hollow box", placemark.getName());
		final Polygon polygon = (Polygon) placemark.getGeometry();
		Assertions.assertTrue(polygon.isExtrude());
		Assertions.assertEquals(AltitudeMode.RELATIVE_TO_GROUND, polygon.getAltitudeMode());

		Assertions.assertEquals(new Coordinate(-122.366278, 37.818844, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(0));
		Assertions.assertEquals(new Coordinate(-122.365248, 37.819267, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(1));
		Assertions.assertEquals(new Coordinate(-122.365640, 37.819861, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(2));
		Assertions.assertEquals(new Coordinate(-122.366669, 37.819429, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(3));
		Assertions.assertEquals(new Coordinate(-122.366278, 37.818844, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(4));

		Assertions.assertEquals(new Coordinate(-122.366212, 37.818977, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(0));
		Assertions.assertEquals(new Coordinate(-122.365424, 37.819294, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(1));
		Assertions.assertEquals(new Coordinate(-122.365704, 37.819731, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(2));
		Assertions.assertEquals(new Coordinate(-122.366488, 37.819402, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(3));
		Assertions.assertEquals(new Coordinate(-122.366212, 37.818977, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(4));

	}

	public static void polyStyle(final PolyStyle polystyle) {
		Assertions.assertEquals("ID", polystyle.getId());

		// <!-- inherited from ColorStyle -->
		Assertions.assertEquals("ffffffff", polystyle.getColor());
		Assertions.assertEquals(ColorMode.NORMAL, polystyle.getColorMode());

		// <!-- specific to PolyStyle -->
		Assertions.assertTrue(polystyle.isFill());
		Assertions.assertTrue(polystyle.isOutline());
	}

	public static void polyStyleExample(final Kml kml) {
		Assertions.assertEquals("PolygonStyle.kml", kml.getFeature().getName());
		Assertions.assertTrue(kml.getFeature().isOpen());

		final Style style = ((Style) kml.getFeature().getStyleSelector().get(0));
		Assertions.assertEquals("examplePolyStyle", style.getId());
		Assertions.assertEquals("ff0000cc", style.getPolyStyle().getColor());
		Assertions.assertEquals(ColorMode.RANDOM, style.getPolyStyle().getColorMode());

		final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("hollow box", placemark.getName());
		Assertions.assertEquals("#examplePolyStyle", placemark.getStyleUrl());

		final Polygon polygon = (Polygon) placemark.getGeometry();
		Assertions.assertTrue(polygon.isExtrude());
		Assertions.assertEquals(AltitudeMode.RELATIVE_TO_GROUND, polygon.getAltitudeMode());

		Assertions.assertEquals(new Coordinate(-122.3662784465226, 37.81884427772081, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(0));
		Assertions.assertEquals(new Coordinate(-122.3652480684771, 37.81926777010555, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(1));
		Assertions.assertEquals(new Coordinate(-122.365640222455, 37.81986126286519, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(2));
		Assertions.assertEquals(new Coordinate(-122.36666937925, 37.81942987753481, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(3));
		Assertions.assertEquals(new Coordinate(-122.3662784465226, 37.81884427772081, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
		    .getCoordinates().get(4));

		Assertions.assertEquals(new Coordinate("-122.366212593918,37.81897719083808,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(0));
		Assertions.assertEquals(new Coordinate("-122.3654241733188,37.81929450992014,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(1));
		Assertions.assertEquals(new Coordinate("-122.3657048517827,37.81973175302663,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(2));
		Assertions.assertEquals(new Coordinate("-122.3664882465854,37.81940249291773,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(3));
		Assertions.assertEquals(new Coordinate("-122.366212593918,37.81897719083808,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
		    .get(4));
	}

	public static void region(final Region region) {
		Assertions.assertEquals("ID", region.getId());

		Assertions.assertEquals(0.0, region.getLatLonAltBox().getNorth(), 0.0001);
		Assertions.assertEquals(0.0, region.getLatLonAltBox().getSouth(), 0.0001);
		Assertions.assertEquals(0.0, region.getLatLonAltBox().getEast(), 0.0001);
		Assertions.assertEquals(0.0, region.getLatLonAltBox().getWest(), 0.0001);
		Assertions.assertEquals(0.0, region.getLatLonAltBox().getMinAltitude(), 0.0001);
		Assertions.assertEquals(0.0, region.getLatLonAltBox().getMaxAltitude(), 0.0001);
		Assertions.assertEquals(AltitudeMode.CLAMP_TO_GROUND, region.getLatLonAltBox().getAltitudeMode());

		Assertions.assertEquals(0.0, region.getLod().getMinLodPixels(), 0.0001);
		Assertions.assertEquals(-1.0, region.getLod().getMaxLodPixels(), 0.0001);
		Assertions.assertEquals(0.0, region.getLod().getMinFadeExtent(), 0.0001);
		Assertions.assertEquals(0.0, region.getLod().getMaxFadeExtent(), 0.0001);

	}

	public static void regionLatLonAltBox(final LatLonAltBox latlonAltBox) {
		Assertions.assertEquals(43.374, latlonAltBox.getNorth(), 0.0001);
		Assertions.assertEquals(42.983, latlonAltBox.getSouth(), 0.0001);
		Assertions.assertEquals(-0.335, latlonAltBox.getEast(), 0.0001);
		Assertions.assertEquals(-1.423, latlonAltBox.getWest(), 0.0001);
		Assertions.assertEquals(0.0, latlonAltBox.getMinAltitude(), 0.0001);
		Assertions.assertEquals(0.0, latlonAltBox.getMaxAltitude(), 0.0001);
	}

	public static void regionLod(final Lod lod) {
		Assertions.assertEquals(256.0, lod.getMinLodPixels(), 0.0001);
		Assertions.assertEquals(-1.0, lod.getMaxLodPixels(), 0.0001);
		Assertions.assertEquals(0.0, lod.getMinFadeExtent(), 0.0001);
		Assertions.assertEquals(0.0, lod.getMaxFadeExtent(), 0.0001);

	}

	public static void regionExample(final Region region) {
		Assertions.assertEquals(50.625, region.getLatLonAltBox().getNorth(), 0.0001);
		Assertions.assertEquals(45.0, region.getLatLonAltBox().getSouth(), 0.0001);
		Assertions.assertEquals(28.125, region.getLatLonAltBox().getEast(), 0.0001);
		Assertions.assertEquals(22.5, region.getLatLonAltBox().getWest(), 0.0001);
		Assertions.assertEquals(10.0, region.getLatLonAltBox().getMinAltitude(), 0.0001);
		Assertions.assertEquals(50.0, region.getLatLonAltBox().getMaxAltitude(), 0.0001);

		Assertions.assertEquals(128.0, region.getLod().getMinLodPixels(), 0.0001);
		Assertions.assertEquals(1024.0, region.getLod().getMaxLodPixels(), 0.0001);
		Assertions.assertEquals(128.0, region.getLod().getMinFadeExtent(), 0.0001);
		Assertions.assertEquals(128.0, region.getLod().getMaxFadeExtent(), 0.0001);
	}

	public static void schema(final Schema schema) {
		Assertions.assertEquals("ID", schema.getId());
		Assertions.assertEquals("string", schema.getName());
		Assertions.assertEquals("string", schema.getSimpleField().get(0).getType());
		Assertions.assertEquals("string", schema.getSimpleField().get(0).getName());
		Assertions.assertEquals("...", schema.getSimpleField().get(0).getDisplayName());
	}

	public static void schemaExample(final Kml kml) {
		final Schema schema = ((Document) kml.getFeature()).getSchema().get(0);
		Assertions.assertEquals("TrailHeadTypeId", schema.getId());
		Assertions.assertEquals("TrailHeadType", schema.getName());

		Assertions.assertEquals("string", schema.getSimpleField().get(0).getType());
		Assertions.assertEquals("TrailHeadName", schema.getSimpleField().get(0).getName());
		Assertions.assertEquals("<![CDATA[<b>Trail Head Name</b>]]>", schema.getSimpleField().get(0).getDisplayName());

		Assertions.assertEquals("double", schema.getSimpleField().get(1).getType());
		Assertions.assertEquals("TrailLength", schema.getSimpleField().get(1).getName());
		Assertions.assertEquals("<![CDATA[<i>The length in miles</i>]]>", schema.getSimpleField().get(1).getDisplayName());

		Assertions.assertEquals("int", schema.getSimpleField().get(2).getType());
		Assertions.assertEquals("ElevationGain", schema.getSimpleField().get(2).getName());
		Assertions.assertEquals("<![CDATA[<i>change in altitude</i>]]>", schema.getSimpleField().get(2).getDisplayName());

	}

	@SuppressWarnings("deprecation")
	public static void screenOverlay(final ScreenOverlay screenoverlay) {
		// <!-- inherited from Feature element -->
		Assertions.assertEquals("...", screenoverlay.getName());
		Assertions.assertTrue(screenoverlay.isVisibility());
		Assertions.assertFalse(screenoverlay.isOpen());
		Assertions.assertNotNull(screenoverlay.getAtomAuthor());
		Assertions.assertNotNull(screenoverlay.getAtomLink());
		Assertions.assertEquals("...", screenoverlay.getAddress());
		Assertions.assertNotNull(screenoverlay.getXalAddressDetails());
		Assertions.assertEquals("...", screenoverlay.getPhoneNumber());
		Assertions.assertEquals(2, screenoverlay.getSnippet().getMaxLines());
		Assertions.assertEquals("...", screenoverlay.getSnippet().getValue());
		Assertions.assertNotNull(screenoverlay.getAbstractView());
		Assertions.assertNotNull(screenoverlay.getTimePrimitive());
		Assertions.assertEquals("...", screenoverlay.getStyleUrl());
		Assertions.assertNotNull(screenoverlay.getStyleSelector());
		Assertions.assertNotNull(screenoverlay.getRegion());
		Assertions.assertNotNull(screenoverlay.getMetadata());
		Assertions.assertNotNull(screenoverlay.getExtendedData());

		// <!-- inherited from Overlay element -->
		Assertions.assertEquals("ffffffff", screenoverlay.getColor());
		Assertions.assertEquals(0, screenoverlay.getDrawOrder());
		Assertions.assertEquals("...", screenoverlay.getIcon().getHref());

		// <!-- specific to ScreenOverlay -->
		Assertions.assertEquals(0.5, screenoverlay.getOverlayXY().getX(), 0.0001);
		Assertions.assertEquals(0.5, screenoverlay.getOverlayXY().getY(), 0.0001);
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getOverlayXY().getXunits());
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getOverlayXY().getYunits());

		Assertions.assertEquals(0.5, screenoverlay.getScreenXY().getX(), 0.0001);
		Assertions.assertEquals(0.5, screenoverlay.getScreenXY().getY(), 0.0001);
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getScreenXY().getXunits());
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getScreenXY().getYunits());

		Assertions.assertEquals(0.5, screenoverlay.getSize().getX(), 0.0001);
		Assertions.assertEquals(0.5, screenoverlay.getSize().getY(), 0.0001);
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getSize().getXunits());
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getSize().getYunits());

		Assertions.assertEquals(0, screenoverlay.getRotation(), 0.0001);

	}

	public static void screenOverlayExample(final ScreenOverlay screenoverlay) {
		Assertions.assertEquals("khScreenOverlay756", screenoverlay.getId());
		Assertions.assertEquals("Simple crosshairs", screenoverlay.getName());
		Assertions.assertEquals("This screen overlay uses fractional positioning to put the image in the exact center of the screen", screenoverlay
		    .getDescription());

		Assertions.assertEquals("http://myserver/myimage.jpg", screenoverlay.getIcon().getHref());

		// <!-- specific to ScreenOverlay -->
		Assertions.assertEquals(0.5, screenoverlay.getOverlayXY().getX(), 0.0001);
		Assertions.assertEquals(0.5, screenoverlay.getOverlayXY().getY(), 0.0001);
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getOverlayXY().getXunits());
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getOverlayXY().getYunits());

		Assertions.assertEquals(0.5, screenoverlay.getScreenXY().getX(), 0.0001);
		Assertions.assertEquals(0.5, screenoverlay.getScreenXY().getY(), 0.0001);
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getScreenXY().getXunits());
		Assertions.assertEquals(Units.FRACTION, screenoverlay.getScreenXY().getYunits());

		Assertions.assertEquals(39.37878630116985, screenoverlay.getRotation(), 0.0001);

		Assertions.assertEquals(0, screenoverlay.getSize().getX(), 0.0001);
		Assertions.assertEquals(0, screenoverlay.getSize().getY(), 0.0001);
		Assertions.assertEquals(Units.PIXELS, screenoverlay.getSize().getXunits());
		Assertions.assertEquals(Units.PIXELS, screenoverlay.getSize().getYunits());
	}

	public static void style(final Style style) {
		Assertions.assertEquals("ID", style.getId());

		// <!-- specific to Style -->
		Assertions.assertNotNull(style.getIconStyle());
		Assertions.assertNotNull(style.getLabelStyle());
		Assertions.assertNotNull(style.getLineStyle());
		Assertions.assertNotNull(style.getPolyStyle());
		Assertions.assertNotNull(style.getBalloonStyle());
		Assertions.assertNotNull(style.getListStyle());
	}

	public static void styleExample(final Kml kml) {
		final Style style = ((Style) kml.getFeature().getStyleSelector().get(0));
		Assertions.assertEquals("myDefaultStyles", style.getId());

		Assertions.assertEquals("a1ff00ff", style.getIconStyle().getColor());
		Assertions.assertEquals(1.399999976158142, style.getIconStyle().getScale(), 0.0001);
		Assertions.assertEquals("http://myserver.com/icon.jpg", style.getIconStyle().getIcon().getHref());

		Assertions.assertEquals("7fffaaff", style.getLabelStyle().getColor());
		Assertions.assertEquals(1.5, style.getLabelStyle().getScale(), 0.0001);

		Assertions.assertEquals("ff0000ff", style.getLineStyle().getColor());
		Assertions.assertEquals(15.0, style.getLineStyle().getWidth(), 0.0001);

		Assertions.assertEquals("7f7faaaa", style.getPolyStyle().getColor());
		Assertions.assertEquals(ColorMode.RANDOM, style.getPolyStyle().getColorMode());

		final Placemark p1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("Google Earth - New Polygon", p1.getName());
		Assertions.assertEquals("Here is some descriptive text", p1.getDescription());
		Assertions.assertEquals("#myDefaultStyles", p1.getStyleUrl());

		final Placemark p2 = (Placemark) ((Document) kml.getFeature()).getFeature().get(1);
		Assertions.assertEquals("Google Earth - New Path", p2.getName());
		Assertions.assertEquals("#myDefaultStyles", p2.getStyleUrl());

	}

	public static void styleMap(final StyleMap stylemap) {
		Assertions.assertEquals("ID", stylemap.getId());

		// <!-- extends StyleSelector -->
		// <!-- elements specific to StyleMap -->
		Assertions.assertEquals("ID", stylemap.getPair().get(0).getId());
		Assertions.assertEquals(StyleState.NORMAL, stylemap.getPair().get(0).getKey());
		Assertions.assertEquals("...", stylemap.getPair().get(0).getStyleUrl());

	}

	public static void styleMapPair(final Pair pair) {
		Assertions.assertEquals(StyleState.NORMAL, pair.getKey());
		Assertions.assertEquals("http://myserver.com/populationProject.xml#example_style_off", pair.getStyleUrl());
	}

	public static void styleMapExample(final Kml kml) {
		Assertions.assertEquals("StyleMap.kml", kml.getFeature().getName());
		Assertions.assertTrue(kml.getFeature().isOpen());

		final Style style1 = ((Style) kml.getFeature().getStyleSelector().get(0));
		Assertions.assertEquals("normalState", style1.getId());
		Assertions.assertEquals(1.0, style1.getIconStyle().getScale(), 0.0001);
		Assertions.assertEquals("http://maps.google.com/mapfiles/kml/pal3/icon55.png", style1.getIconStyle().getIcon().getHref());
		Assertions.assertEquals(1.0, style1.getLabelStyle().getScale(), 0.0001);

		final Style style2 = ((Style) kml.getFeature().getStyleSelector().get(1));
		Assertions.assertEquals("highlightState", style2.getId());
		Assertions.assertEquals(1.1, style2.getIconStyle().getScale(), 0.0001);
		Assertions.assertEquals("http://maps.google.com/mapfiles/kml/pal3/icon60.png", style2.getIconStyle().getIcon().getHref());
		Assertions.assertEquals(1.1, style2.getLabelStyle().getScale(), 0.0001);
		Assertions.assertEquals("ff0000c0", style2.getLabelStyle().getColor());

		final StyleMap stylemap = (StyleMap) kml.getFeature().getStyleSelector().get(2);
		Assertions.assertEquals("styleMapExample", stylemap.getId());
		Assertions.assertEquals(StyleState.NORMAL, stylemap.getPair().get(0).getKey());
		Assertions.assertEquals("#normalState", stylemap.getPair().get(0).getStyleUrl());
		Assertions.assertEquals(StyleState.HIGHLIGHT, stylemap.getPair().get(1).getKey());
		Assertions.assertEquals("#highlightState", stylemap.getPair().get(1).getStyleUrl());

		final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
		Assertions.assertEquals("StyleMap example", placemark.getName());
		Assertions.assertEquals("#styleMapExample", placemark.getStyleUrl());
		Assertions.assertEquals(new Coordinate(-122.368987, 37.817634, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));

	}

	public static void timeSpan(final TimeSpan timespan) {
		Assertions.assertEquals("...", timespan.getBegin());
		Assertions.assertEquals("...", timespan.getEnd());

	}

	public static void timeStamp(final TimeStamp timestamp) {
		Assertions.assertEquals("...", timestamp.getWhen());
	}

	public static void updateChange(final NetworkLinkControl networklink) {
		final Update update = networklink.getUpdate();
		Assertions.assertEquals("http://www/~sam/January14Data/Point.kml", update.getTargetHref());

		final Change change = (Change) update.getCreateOrDeleteOrChange().get(0);
		final Point point = (Point) change.getAbstractObject().get(0);
		Assertions.assertEquals("point123", point.getTargetId());
		Assertions.assertEquals(new Coordinate(-95.48, 40.43, 0.0), point.getCoordinates().get(0));

	}

	public static void updateCreate(final Update update) {
		Assertions.assertEquals("http://myserver.com/Point.kml", update.getTargetHref());

		final Create create = (Create) update.getCreateOrDeleteOrChange().get(0);
		final Document document = (Document) create.getContainer().get(0);
		Assertions.assertEquals("region24", document.getTargetId());
		final Placemark placemark = (Placemark) document.getFeature().get(0);
		Assertions.assertEquals("placemark891", placemark.getId());
		Assertions.assertEquals(new Coordinate(-95.48, 40.43, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
	}

	public static void updateDelete(final Update update) {
		Assertions.assertEquals("http://www.foo.com/Point.kml", update.getTargetHref());

		final Delete delete = (Delete) update.getCreateOrDeleteOrChange().get(0);
		final Placemark placemark = (Placemark) delete.getFeature().get(0);
		Assertions.assertEquals("pa3556", placemark.getTargetId());
	}



}
