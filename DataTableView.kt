import javafx.beans.value.ChangeListener
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Region

class DataTableView (
    private val model: Model
) : GridPane(), IView {
    private fun displayDataTable(data : MutableList<Int>?) {
        // keep a row counter to make grid layout easier to change
        var r = 0

        children.clear()
        for (i in data!!.indices) {
            val label = Label("${i+1}:")
            add(label, 0, r, 1, 1)

            val valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, data.elementAt(i), 1)
            val spinner = Spinner(valueFactory)
            spinner.valueProperty().addListener{
                observable,old,new ->
                model.changeData(i,spinner.value)
            }

            //ensure that the label is always rendered with a fixed width that will fit all the text
            label.setMinWidth(Region.USE_PREF_SIZE)
            label.setMaxWidth(Region.USE_PREF_SIZE)
            add(spinner, 1, r, 1, 1)
            r++

        }
    }
    override fun updateView() {
        val dataset = model.dataSets[model.curDatasetName]
        var data = dataset?.data
        displayDataTable(data)
    }

    init {
        prefWidth = 150.0
        padding = Insets(10.0)
        updateView()

        hgap = 10.0
        model.addView(this)
    }
}