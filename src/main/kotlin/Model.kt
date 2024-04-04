import kotlin.random.Random

class Model {
    // all views of this model
    private val views: ArrayList<IView> = ArrayList()
    var curDatasetNum = 0 // for "Dataset:"
    var curDatasetName = "Increasing"
    var X = 1
    fun addView(view: IView) {
        views.add(view)
        view.updateView()
    }

    // the model uses this method to notify all of the Views that the data has changed
    // the expectation is that the Views will refresh themselves to display new data when appropriate
    private fun notifyObservers() {
        for (view in views) {
            view.updateView()
        }
    }

    // changes when new button is clicked
    fun createNewXDataset(n: Int) {
        curDatasetName = "New" + X
        X++
        var newData = mutableListOf<Int>()
        for(i in (1..n)) {
            newData.add(Random.nextInt(0,100))
        }
        val newTitle = LoremIpsum.getRandomSequence(3)
        val newXAxis = LoremIpsum.getRandomSequence(1)
        val newYAxis = LoremIpsum.getRandomSequence(1)
        val newDataSet = DataSet(
            newTitle, newXAxis, newYAxis,
            newData
        )
        dataSets[curDatasetName] = newDataSet
        curDatasetNum = dataSets.size - 1
        notifyObservers()
    }

    // the DataSets in DataSet.kt
    val dataSets = mutableMapOf("Increasing" to createTestDataSet("Increasing"),
        "Large" to createTestDataSet("Large"), "Middle" to createTestDataSet("Middle"),
        "Single" to createTestDataSet("Single"), "Range" to createTestDataSet("Range"),
        "Percentage" to createTestDataSet("Percentage"))

    fun changeCurDatasetNum(num:Int) {
        curDatasetNum = num
        curDatasetName = dataSets.keys.elementAt(num)
        notifyObservers()
    }

    fun changeData(i : Int, value : Int) {
        dataSets[curDatasetName]!!.data[i] = value
        notifyObservers()
    }
    fun changeYValues(yVal : String) {
        dataSets[curDatasetName]!!.yAxis = yVal
        notifyObservers()
    }
    fun changeXValues(xVal : String) {
        dataSets[curDatasetName]!!.xAxis = xVal
        notifyObservers()
    }
    fun changeTitle(title : String) {
        dataSets[curDatasetName]!!.title = title
        notifyObservers()
    }
}