package devoxx
import org.joda.time.{DateTimeZone, LocalDateTime}
import unfiltered.filter.Plan
import unfiltered.request.{GET, Seg, Path}
import unfiltered.response.{ResponseString, CharContentType, Ok}

class CalendarPlan extends Plan{

  def intent = {
    case GET(Path(Seg("matches" :: Nil)))
      => Ok ~> CharContentType("text/calendar") ~> ResponseString(matchCal)
  }

  def matchCal = {
    val m = aMatch
    val start = osloToUtc(m.date)
    VCalendar(
      VEvent(m.teams, m.referees,start,
            start.plusMinutes(90+15),m.venue)
    ).feed
  }

  private def osloToUtc(dateTime: LocalDateTime) = {
    dateTime.toDateTime(DateTimeZone.forID("Europe/Oslo"))
      .withZone(DateTimeZone.UTC).toLocalDateTime
  }

  private def aMatch = AssignedMatch(
    new LocalDateTime(2013,3,3,17,00),
    "Prm","", "Tottenham - Arsenal","WHL","Morten Andersen-Gott",""
  )

}
