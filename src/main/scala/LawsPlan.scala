package devoxx
import unfiltered.filter.Plan
import unfiltered.request.{Seg, Path, GET}
import unfiltered.response.{Html5, Ok}

class LawsPlan extends Plan {
  def intent = {
    case GET(Path(Seg(Nil))) => Ok ~> Html5(<a href="/laws">Laws of the game</a>)
    case GET(Path(Seg("laws" :: "11" :: Nil))) => Ok ~> Html5(offside)
    case GET(Path(Seg("laws" :: Nil))) => Ok ~> Html5(Snippets.index)
  }

  def offside = {
    val noOffsides = List("Goal kick","Corner kick","Throw in")
    <h1>No offside from</h1>
    <table>
      {
      noOffsides.map(s => <tr><td>{s}</td></tr>)
      }
    </table>
  }

}
