package devoxx;

import xml.NodeSeq

object Snippets {

  def index = emptyPage(
    <h1>Laws</h1>
      <ol>
        <li>The field of play</li>
        <li>The ball</li>
      </ol>
  )

  def emptyPage(body: NodeSeq) = {
    <html>
      <head>
        <title>Laws of the game</title>
      </head>
      <body>
        {body}
      </body>
    </html>
  }
}
