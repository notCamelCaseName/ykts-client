import UtilityClasses.Point;

import java.util.Objects;
import java.util.Vector;

public class Footprint {
    private static final double keyPointsThreshold = .7;
    private static final double anchorThreshold = .95;
    private static final double targetTimeOffset = 1.;
    private static final double targetTimeWidth = 1.;
    private static final double targetFrequencyOffset = 1.;
    private static final double targetFrequencyWidth = 1.;

    private final Point anchor;
    private final Vector<Point> targetPoints;

    public Footprint(Point keyPoint, Vector<Point> targetPoints) {
        this.anchor = keyPoint;
        this.targetPoints = targetPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Footprint footprint = (Footprint) o;
        return Objects.equals(anchor, footprint.anchor) && Objects.equals(targetPoints, footprint.targetPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anchor, targetPoints);
    }

    public static Vector<Footprint> extractFootprints(Spectrogram spectrogram) {
        Vector<Point> keyPoints = spectrogram.getKeyPoints(keyPointsThreshold);
        Vector<Point> anchors = getAnchors(keyPoints);
        Vector<Footprint> footprints = new Vector<>();
        for (Point a : anchors) {
            footprints.add(new Footprint(a, getTargetPoints(keyPoints, a)));
        }
        return footprints;
    }

    private static Vector<Point> getAnchors(Vector<Point> keyPoints) {
        Vector<Point> anchors = new Vector<>();
        for (Point kp : keyPoints) {
            if (kp.z > anchorThreshold) {
                anchors.add(kp);
            }
        }
        return anchors;
    }

    private static Vector<Point> getTargetPoints(Vector<Point> keyPoints, Point anchor) {
        Vector<Point> targetPoints = new Vector<>();
        for (Point kp : keyPoints) {
            if (anchor.x + targetTimeOffset < kp.x && kp.x < anchor.x + targetTimeOffset + targetTimeWidth &&
                anchor.y + targetFrequencyOffset < kp.y && kp.y < anchor.y + targetFrequencyOffset + targetFrequencyWidth) {
                targetPoints.add(kp);
            }
        }
        return targetPoints;
    }
}
