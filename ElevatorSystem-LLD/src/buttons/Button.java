package buttons;

public abstract class Button {
    private boolean isPressed;

    public Button() {
        this.isPressed = false;
    }

    public abstract void pressed();

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        this.isPressed = pressed;
    }
}
