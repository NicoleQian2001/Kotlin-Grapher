import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.HBox
import javafx.scene.paint.Color

class StatusbarView (
    private val model: Model
) : HBox(), IView {

    override fun updateView() {
        val n = model.dataSets.size
        dataSetsLabel.text = "${n} datasets" // keep tracks of number of datasets
    }
    val dataSetsLabel = Label()
    init {
        padding = Insets(10.0)
        spacing = 10.0
        background = Background(BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY),)

        children.add(dataSetsLabel)
        updateView()
        model.addView(this)
    }
}