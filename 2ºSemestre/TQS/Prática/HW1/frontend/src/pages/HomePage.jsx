import React from 'react'

import Navbar from '../components/Navbar'
import Hero from '../components/Hero'

const HomePage = () => {
  return (
    <main>
      <Navbar />
      <div className='bg-white h-screen'>
        <Hero />
      </div>
    </main>
  )
}

export default HomePage