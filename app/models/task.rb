class Task < ActiveRecord::Base
  belongs_to :task_user
  validates :due_date, presence: true
end
