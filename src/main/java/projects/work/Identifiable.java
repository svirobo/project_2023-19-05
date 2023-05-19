package projects.work;

/**
 * Demands numeric identification of any object of the class that implements this interface.
 * This interface is intended to be used by any class that we want to store into {@link Database}.
 */
public interface Identifiable {
    /**
     * @return A numeric identification of given object
     */
    Number identify();
}
