
@org.hibernate.annotations.FilterDefs({
		@org.hibernate.annotations.FilterDef(name = "limitByUserRanking", parameters = {
				@org.hibernate.annotations.ParamDef(name = "currentUserRanking", type = "int") }
		), @org.hibernate.annotations.FilterDef(name = "limitByUserRankingDefault", defaultCondition = """
				:currentUserRanking >= (
				        select u.RANKING from USERS u
				        where u.ID = SELLER_ID
				    )""", parameters = {
				@org.hibernate.annotations.ParamDef(name = "currentUserRanking", type = "int") }) })

package com.utk.entity;
