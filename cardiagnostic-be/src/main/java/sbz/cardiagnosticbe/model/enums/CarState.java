package sbz.cardiagnosticbe.model.enums;

public enum CarState {
    START_UP, RUNNABLE, MOVEMENT;

    public static CarState fromInteger(int x) {
        switch(x) {
            case 0:
                return START_UP;
            case 1:
                return RUNNABLE;
            case 2:
                return  MOVEMENT;
        }
        return null;
    }
}
