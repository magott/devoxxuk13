import devoxx.AssignedMatch
import org.joda.time.LocalDateTime
import unfiltered.request.{GET, Seg, Path, Method}
import unfiltered.response.{ResponseString, CharContentType, Ok}

class VerboseCalendarPlan {

  def intent = {
    case GET(Path(Seg("matches" :: Nil)))
    => Ok ~> CharContentType("text/calendar") ~> ResponseString(vCal(listOfMatches))
  }

  def vCal(ms:List[AssignedMatch]) = ms.foldLeft(header)(_ + "\n" + asVEvent(_)) + "\n" + footer

  def asVEvent(m:AssignedMatch) = {
    """BEGIN:VEVENT
      |DTSTART:%s
      |DTEND:%s
      |DTSTAMP:%s
      |LAST-MODIFIED:%s
      |UID:%s
      |LOCATION:%s
      |SUMMARY:%s
      |DESCRIPTION:%s
      |END:VEVENT""".stripMargin.format(
      m.date.toString(calendarFormatString),
      m.date.plusMinutes(90+15).toString(calendarFormatString),
      LocalDateTime.now.toString(calendarFormatString),
      LocalDateTime.now.toString(calendarFormatString),
      m.fiksId + "@andersen-gott.com",
      m.venue,
      m.teams,
      m.referees)
  }

  val header =
    """BEGIN:VCALENDAR
      |VERSION:2.0
      |PRODID:-//andersen-gott.com/demo//NONSGML v1.0//EN
      |METHOD:PUBLISH
      |X-WR-CALNAME:DEVOXX-demo
      |X-WR-CALDESC:DEVOXX-demo
      |X-PUBLISHED-TTL:PT1H""".stripMargin

  val footer = "END:VCALENDAR"

}



