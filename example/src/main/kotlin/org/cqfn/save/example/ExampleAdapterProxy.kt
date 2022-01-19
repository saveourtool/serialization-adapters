package org.cqfn.save.example

import org.cqfn.save.adapter.Adapter
import org.cqfn.save.adapter.AdapterProxy

class ExampleAdapterProxy : AdapterProxy {
    override fun adapter(): Adapter<out Any> {
        return ExampleAdapter()
    }
}
