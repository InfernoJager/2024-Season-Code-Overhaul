package frc.robot.models;

public class OdometryReading {
    static public OdometryReading from_limelight(double[] limelight_data, OdometryReading previous_reading, double time_interval) {
        CoordinateReading position = new CoordinateReading(limelight_data[0], limelight_data[1], limelight_data[2]);
        CoordinateReading rotation = new CoordinateReading(limelight_data[3], limelight_data[4], limelight_data[5]);

        CoordinateReading velocity = position.get_velocity(previous_reading.position(), time_interval);
        CoordinateReading angular_velocity = rotation.get_velocity(previous_reading.rotation(), time_interval);

        return new OdometryReading(position, rotation, velocity, angular_velocity);
    }

    static public OdometryReading baseline_from_limelight(double[] limelight_data) {
        CoordinateReading position = new CoordinateReading(limelight_data[0], limelight_data[1], limelight_data[2]);
        CoordinateReading rotation = new CoordinateReading(limelight_data[3], limelight_data[4], limelight_data[5]);

        CoordinateReading velocity = new CoordinateReading(0, 0, 0);
        CoordinateReading angular_velocity = new CoordinateReading(0, 0, 0);

        return new OdometryReading(position, rotation, velocity, angular_velocity);
    }

    private CoordinateReading m_position, m_rotation, m_velocity, m_angular_velocity;

    public OdometryReading(CoordinateReading position, CoordinateReading rotation, CoordinateReading velocity, CoordinateReading angular_velocity) {
        m_position = position;
        m_rotation = rotation;
        m_velocity = velocity;
        m_angular_velocity = angular_velocity;
    }

    public double x() {
        return m_position.x();
    }

    public double y() {
        return m_position.y();
    }

    public double z() {
        return m_position.z();
    }

    public CoordinateReading position() {
        return m_position;
    }

    public double roll() {
        return m_rotation.x();
    }

    public double pitch() {
        return m_rotation.y();
    }

    public double yaw() {
        return m_rotation.z();
    }

    public CoordinateReading rotation() {
        return m_rotation;
    }

    public double x_velocity() {
        return m_velocity.x();
    }

    public double y_velocity() {
        return m_velocity.y();
    }

    public double z_velocity() {
        return m_velocity.z();
    }

    public double roll_velocity() {
        return m_angular_velocity.x();
    }

    public double pitch_velocity() {
        return m_angular_velocity.y();
    }

    public double yaw_velocity() {
        return m_angular_velocity.z();
    }
}

class CoordinateReading {
    private double coor_x, coor_y, coor_z;

    public CoordinateReading(double x, double y, double z) {
        coor_x = x;
        coor_y = y;
        coor_z = z;
    }

    public double x() {
        return coor_x;
    }

    public double y() {
        return coor_y;
    }

    public double z() {
        return coor_z;
    }

    public CoordinateReading get_velocity(CoordinateReading old_reading, double time_interval) {
        return new CoordinateReading(
            this.x() - old_reading.x(),
            this.y() - old_reading.y(),
            this.z() - old_reading.z()
        );
    }
}
