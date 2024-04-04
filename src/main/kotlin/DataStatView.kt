import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.GridPane

class DataStatView (
    private val model: Model,
    private val controller: Main
) : GridPane(), IView {
    override fun updateView() {
        displayValues()
    }
    val num = Label()
    val min = Label()
    val max = Label()
    val avg = Label()
    val sum = Label()

    // calculate statistics
    fun displayValues() {
        // get selected dataset
        val dataset = model.dataSets[model.curDatasetName]
        var avgCalculated = 0.0
        if (dataset != null) {avgCalculated = Math.round(dataset?.data?.average() * 10.0)/10.0}
        num.text = dataset?.data?.size.toString()
        min.text = dataset?.data?.minByOrNull { it.toFloat() }.toString()
        max.text = dataset?.data?.maxByOrNull { it.toFloat() }.toString()
        avg.text = avgCalculated.toString()
        sum.text = dataset?.data?.sum().toString()

    }
    init {
        prefWidth = 125.0
        padding = Insets(10.0)
        // keep a row counter to make grid layout easier to change
        var r = 0
        val numLabel = Label("Number")
        hgap = 10.0
        vgap = 10.0
        alignment = Pos.TOP_LEFT // columns are left-aligned


        add(numLabel, 0, r, 1, 1)

        add(num, 1, r, 1, 1)
        r++

        val minLabel = Label("Minimum")
        add(minLabel, 0, r, 1, 1)
        add(min, 1, r, 1, 1)
        r++

        val maxLabel = Label("Maximum")
        add(maxLabel, 0, r, 1, 1)
        add(max, 1, r, 1, 1)
        r++

        val avgLabel = Label("Average")
        add(avgLabel, 0, r, 1, 1)
        add(avg, 1, r, 1, 1)
        r++

        val sumLabel = Label("Sum")
        add(sumLabel, 0, r, 1, 1)
        add(sum, 1, r, 1, 1)
        r++
        displayValues()
        model.addView(this)
    }
}
