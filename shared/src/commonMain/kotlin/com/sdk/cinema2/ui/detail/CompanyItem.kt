package com.sdk.cinema2.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sdk.cinema2.data.model.Company
import com.sdk.cinema2.ui.component.NetworkImage

@Composable
fun CompanyItem(company: Company) {
    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            url = company.logo ?: "kuUIHNwMec4dwOLghDhhZJzHZTd.png",
            modifier = Modifier.size(140.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = company.name
        )
    }
}