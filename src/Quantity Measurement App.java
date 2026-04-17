class QuantityMeasurementApp {

    // Step 1: Extend Enum with New Units
    enum LengthUnit {

        // Base unit: FEET
        FEET(1.0),

        // 1 inch = 1/12 feet
        INCHES(1.0 / 12.0),

        // 1 yard = 3 feet
        YARDS(3.0),

        // 1 cm = 0.393701 inches
        // inches → feet conversion applied
        CENTIMETERS(0.393701 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Step 2: Generic Quantity Class (Same as UC3)
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert to Base Unit (Feet)
        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        // Equality Check
        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(
                    this.toBaseUnit(),
                    other.toBaseUnit()
            ) == 0;
        }
    }

    // Main Method (Testing UC4)
    public static void main(String[] args) {

        // Yard to Feet
        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength q2 =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(
                "1 Yard == 3 Feet : " + q1.equals(q2)
        );

        // Yard to Inches
        QuantityLength q3 =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength q4 =
                new QuantityLength(36.0, LengthUnit.INCHES);

        System.out.println(
                "1 Yard == 36 Inches : " + q3.equals(q4)
        );

        // Centimeter to Inches
        QuantityLength q5 =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength q6 =
                new QuantityLength(0.393701, LengthUnit.INCHES);

        System.out.println(
                "1 cm == 0.393701 inches : "
                        + q5.equals(q6)
        );

        // Same Unit Check
        QuantityLength q7 =
                new QuantityLength(2.0, LengthUnit.YARDS);

        QuantityLength q8 =
                new QuantityLength(2.0, LengthUnit.YARDS);

        System.out.println(
                "2 Yard == 2 Yard : "
                        + q7.equals(q8)
        );
    }
}