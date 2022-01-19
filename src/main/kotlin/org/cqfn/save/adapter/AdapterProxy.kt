package org.cqfn.save.adapter

interface AdapterProxy {
    fun adapter() : Adapter<out Any>
}
