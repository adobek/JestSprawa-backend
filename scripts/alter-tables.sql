alter table app.IssuesStatuses
alter column date set default NOW();

alter table app.notes
alter column creation_date set default NOW();

