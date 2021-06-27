import Head from 'next/head'
import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { Controls, InsertAuthor, InsertQuote, QuoteBox } from '../components/Styles'

export default function Home() {

  const [clicked, setCliked] = useState(false)
  const [quote, setQuote] = useState({
    _id: "",
    tags: [],
    content: "",
    author: "",
    authorSlug: "",
    length: null
  })

  useEffect(() => {
    axios.get('https://api.quotable.io/random').then(res => {
      setQuote({...res.data})
    }).catch(err => {
      console.log(err)
    })
    /*return () => {
      setCliked(false)
    }*/
  }, [clicked])

  return (
    <div>
      <Head>
        <title>Random Quote Generator</title>
        <meta name="description" content="A simple random quote generator by NextJS" />
        <link rel="icon" href="/favicon.ico" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossOrigin="anonymous" referrerPolicy="no-referrer" />
      </Head>

      <main>
        <QuoteBox id="#quote-box">

          <div className="content">
            <InsertQuote id="text">{quote.content}</InsertQuote>
          </div>

          <Controls>
            <InsertAuthor id="author">- {quote.author}</InsertAuthor>
            <a id="tweet-quote" className="twitter-share-button" href={`https://twitter.com/intent/tweet?text=${quote.content} - ${quote.author}`} target="_blank" title="Tweet this Quote!" rel="noreferrer"><i className="fab fa-twitter fa-3x" aria-hidden="true"></i></a>
          </Controls>

        </QuoteBox>

        <button id="btn new-quote" onClick={() => setCliked(prev => !prev)}>
          <div className={clicked ? "refresh rotate" : "refresh"}></div>
          <p className="next">Next Quote</p>
        </button>
      </main>
      <footer>
        <p className="love">Made with <i className="fa fa-heart heartbeat"></i> by <a href="https://twitter.com/AfiurRahman" target="_blank" rel="noreferrer">Someone</a></p>
      </footer>
    </div>
  )
}
