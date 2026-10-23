interface MetricTemperatureSensor {
    double getTemperatureCelsius();
}

class FahrenheitSensor {
    public double readFahrenheit() {
        return 98.6;
    }
}

public class ClassTemperatureAdapter extends FahrenheitSensor implements MetricTemperatureSensor {
    @Override
    public double getTemperatureCelsius() {
        return (readFahrenheit() - 32) * 5.0 / 9.0;
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        MetricTemperatureSensor classAdapter = new ClassTemperatureAdapter();
        double classCelsius = classAdapter.getTemperatureCelsius();

        FahrenheitSensor sensor = new FahrenheitSensor();
        MetricTemperatureSensor objectAdapter = new ObjectTemperatureAdapter(sensor);
        double objectCelsius = objectAdapter.getTemperatureCelsius();

        System.out.println("Class Adapter Celsius: " + classCelsius);
        System.out.println("Object Adapter Celsius: " + objectCelsius);
        System.out.println("Identical: " + (classCelsius == objectCelsius));
    }
}
