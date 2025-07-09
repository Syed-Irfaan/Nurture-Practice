public class BuilderPatternExample {

    static class Computer {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;

        private Computer(Builder builder) {
            this.cpu = builder.cpu;
            this.ram = builder.ram;
            this.storage = builder.storage;
            this.gpu = builder.gpu;
        }

        public void displayConfig() {
            System.out.println("CPU: " + cpu);
            System.out.println("RAM: " + ram);
            System.out.println("Storage: " + storage);
            System.out.println("GPU: " + gpu);
        }

        static class Builder {
            private String cpu;
            private String ram;
            private String storage;
            private String gpu;

            public Builder setCPU(String cpu) {
                this.cpu = cpu;
                return this;
            }

            public Builder setRAM(String ram) {
                this.ram = ram;
                return this;
            }

            public Builder setStorage(String storage) {
                this.storage = storage;
                return this;
            }

            public Builder setGPU(String gpu) {
                this.gpu = gpu;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    public static void main(String[] args) {
        Computer basicComputer = new Computer.Builder()
            .setCPU("Intel i3")
            .setRAM("4GB")
            .setStorage("256GB SSD")
            .build();

        Computer gamingComputer = new Computer.Builder()
            .setCPU("Intel i9")
            .setRAM("32GB")
            .setStorage("1TB SSD")
            .setGPU("NVIDIA RTX 4090")
            .build();

        System.out.println("=== Basic Computer ===");
        basicComputer.displayConfig();

        System.out.println("\n=== Gaming Computer ===");
        gamingComputer.displayConfig();
    }
}
