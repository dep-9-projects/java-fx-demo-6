import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ObservableListDemo {
    public static void main(String[] args) {
        ObservableList<Integer> olNumbers = FXCollections.observableArrayList();

        olNumbers.addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> change) {
                System.out.println(change);
            }
        });

        olNumbers.add(10);
        olNumbers.add(20);

    }
}
