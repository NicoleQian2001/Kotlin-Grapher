import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox

class TitlepaneView (
    private val model: Model,
    private val controller: Main
) : HBox(), IView {

    override fun updateView() {
        titleField.text = model.dataSets[model.curDatasetName]?.title
        xAxisField.text = model.dataSets[model.curDatasetName]?.xAxis
        yAxisField.text = model.dataSets[model.curDatasetName]?.yAxis
    }
    val titleField = TextField()
    val xAxisField = TextField()
    val yAxisField = TextField()
    init {
        val titleLabel = Label("Title:")
        val xAxisLabel = Label("X-Axis:")
        val yAxisLabel = Label("Y-Axis:")

        /*** update the changes to model ***/
        titleField.textProperty().addListener(){
                observable,old,new ->
            model.changeTitle(new)
        }
        xAxisField.textProperty().addListener(){
                observable,old,new ->
            model.changeXValues(new)
        }
        yAxisField.textProperty().addListener(){
                observable,old,new ->
            model.changeYValues(new)
        }

        alignment = Pos.CENTER_LEFT
        spacing = 10.0
        padding = Insets(10.0)
        children.addAll(titleLabel,titleField,xAxisLabel,xAxisField,yAxisLabel,yAxisField)
        prefHeight = 30.0
        model.addView(this)
    }
}